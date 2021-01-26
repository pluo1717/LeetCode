package T42接雨水;

public class TrappingRainWater {
    public static int getRightMaxIndex(int[] height, int index) {
        for (int i2=index+1; i2<height.length; i2++) {
            if (height[index] <= height[i2]) {
                index = i2;
            }
        }
        return index;
    }

    public int trap(int[] height) {
        int res = 0;
        int leftMaxIndex = 0, rightMaxIndex=getRightMaxIndex(height, 0);
        for(int i=0; i<height.length; i++) {
            if(height[leftMaxIndex]<=height[i]) {
                leftMaxIndex = i;
            }
            if(i >= rightMaxIndex) {
                rightMaxIndex = getRightMaxIndex(height, i);
            }
            int cur = (Math.min(height[leftMaxIndex], height[rightMaxIndex]) - height[i]);
            if (cur > 0) {
                res += cur;
            }
        }
        return res;
    }

    public int trap2(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        int res = 0;
        int maxIndex = 0;
        for (int i=0; i<height.length; i++) {
            if (height[i] > height[maxIndex]) {
                maxIndex = i;
            }
        }

        int curBoundary = height[0];
        for(int i=0; i<=maxIndex; i++) {
            if (curBoundary <= height[i]) {
                curBoundary = height[i];
            } else {
                res += (curBoundary-height[i]);
            }
        }
        curBoundary = height[height.length-1];
        for(int i=height.length-1; i>=maxIndex; i--) {
            if (curBoundary <= height[i]) {
                curBoundary = height[i];
            } else {
                res += (curBoundary-height[i]);
            }
        }

        return res;
    }

    public int trap3(int[] height) {
        if (height.length < 3) {
            return 0;
        }
        int res = 0;
        int leftMax = 0, rightMax = height.length-1;
        int left = 1, right = height.length-2;
        while (left<=right) {
            System.out.printf("%d %d %d %d %d\n", res, leftMax, left, right, rightMax);
            if (height[leftMax] < height[rightMax]) {
                if(height[leftMax] > height[left]) {
                    res += (Math.min(height[leftMax], height[rightMax]) - height[left]);
                } else {
                    leftMax = left;
                }
                left ++;
            } else {
                if (height[right] < height[rightMax]) {
                    res += (Math.min(height[leftMax], height[rightMax]) - height[right]);
                } else {
                    rightMax = right;
                }
                right --;
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        int[] a = {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] a = {2,1,0,2};
        System.out.println(new TrappingRainWater().trap3(a));
    }
}
