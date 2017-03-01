package com.asiainfo.foundation.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class CollectionUtils extends org.springframework.util.CollectionUtils{
	  public static <T> Collection<T> convertTo(Collection<?> collection, Class<T> target)
	  {
	    Collection ret = new ArrayList();
	    for (Iterator localIterator = collection.iterator(); localIterator.hasNext(); ) { Object element = localIterator.next();
	      ret.add(element);
	    }
	    return ret;
	  }

	  public static boolean isNotEmpty(Collection collection) {
	    return !isEmpty(collection);
	  }

	  public static CompareResult sortAndCompare(List left, List right, Comparator comparator)
	  {
	    return sortAndCompare(left, null, right, null, comparator);
	  }

	  public static CompareResult sortAndCompare(List left, Comparator lcomparator, List right, Comparator rcomparator, Comparator comparator)
	  {
	    CompareResult compareResult = new CompareResult();
	    if ((isEmpty(left)) && (isEmpty(right))) {
	      return compareResult;
	    }
	    if ((isEmpty(left)) && (!isEmpty(right))) {
	      compareResult.rightMores = right;
	      return compareResult;
	    }
	    if ((!isEmpty(left)) && (isEmpty(right))) {
	      compareResult.leftMores = left;
	      return compareResult;
	    }
	    if (lcomparator != null) {
	      Collections.sort(left, lcomparator);
	    }
	    else {
	      Collections.sort(left);
	    }
	    if (rcomparator != null) {
	      Collections.sort(right, rcomparator);
	    }
	    else {
	      Collections.sort(right);
	    }
	    int index = 0;
	    for (Iterator localIterator = left.iterator(); localIterator.hasNext(); ) { Object le = localIterator.next();
	      boolean processed = false;
	      while (index < right.size()) {
	        Object re = right.get(index);
	        int result = comparator.compare(le, re);
	        if (result > 0) {
	          compareResult.rightMores.add(re);
	          index++;
	        } else {
	          if (result == 0) {
	            compareResult.leftRemains.add(le);
	            compareResult.rightRemains.add(re);
	            processed = true;
	            index++;
	            break;
	          }

	          compareResult.leftMores.add(le);
	          processed = true;
	          break;
	        }
	      }
	      if ((index >= right.size()) && (!processed)) {
	        compareResult.leftMores.add(le);
	      }
	    }
	    while (index < right.size()) {
	      compareResult.rightMores.add(right.get(index));
	      index++;
	    }
	    return compareResult;
	  }

	  public static class CompareResult {
	    private Collection leftRemains = new ArrayList();
	    private Collection leftMores = new ArrayList();

	    private Collection rightRemains = new ArrayList();
	    private Collection rightMores = new ArrayList();

	    public Collection getLeftRemains() {
	      return this.leftRemains;
	    }
	    public void setLeftRemains(Collection leftRemains) {
	      this.leftRemains = leftRemains;
	    }
	    public Collection getLeftMores() {
	      return this.leftMores;
	    }
	    public void setLeftMores(Collection leftMores) {
	      this.leftMores = leftMores;
	    }
	    public Collection getRightRemains() {
	      return this.rightRemains;
	    }
	    public void setRightRemains(Collection rightRemians) {
	      this.rightRemains = rightRemians;
	    }
	    public Collection getRightMores() {
	      return this.rightMores;
	    }
	    public void setRightMores(Collection rightMores) {
	      this.rightMores = rightMores;
	    }
	  }
}
