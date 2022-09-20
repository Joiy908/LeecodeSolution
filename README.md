---
title: Great ideas
---

# detect overflow

L7 L8

way1: use long，check after operation

```java
class Solution {
    /* reverse x 
      if overflow: return 0;
    */
    public int reverse(int x) {
        long n = 0;
        while(x != 0) {
            n = n*10 + x%10;
            if (x > 0 && n > Integer.MAX_VALUE)
                return 0;
            if (x < 0 && n < Integer.MIN_VALUE)
                return 0;
            x = x/10;
        }
        return (int) n;
    }
}

// or : less code, but more cost of time
class Solution {
    public int reverse(int x) {
        long n = 0;
        while(x != 0) {
            n = n*10 + x%10;
            if ((int) n != n)
                return 0;
            x = x/10;
        }
        return (int) n;
    }
}
```

way2: use “move”:

trun `if (x + 10 > Integer.MAX_VALUE)` to `if(x > Integer.MAX_VALUE - 10)`

```java
public int reverse(int x) {
    int n = 0;
    while(x != 0) {
        if (x > 0 && n > (Integer.MAX_VALUE - x%10)/10)
            return 0;
        if (x < 0 && n < (Integer.MIN_VALUE - x%10)/10)
            return 0;
        n = n*10 + x%10;
        x = x/10;
    }
    return n;
}
```



