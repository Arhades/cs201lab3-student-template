import java.util.*;
import java.util.stream.Stream.*;

public class Q1Test {
     public static void main(String[] args) {

          String result, traversal1, traversal2, traversal3;

          traversal1 = "1-2";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "3-2-1";
          traversal3 = "2-3-1";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "3-1-2-5-4";
          traversal2 = "2-1-4-5-3";
          traversal3 = "1-2-3-4-5";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "10-20-30-40-50";
          traversal2 = "20-10-30-40-50";
          traversal3 = "10-50-40-30-20";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();
     } 

      // write your codes here
     public static boolean isInorder(String traversal) {
          int[] parts = Arrays.stream(traversal.split("-"))
                              .mapToInt(Integer::parseInt)
                              .toArray();

          int len = parts.length;
          for (int i = 0 ; i < len - 1 ; i++) {
               if (parts[i] > parts[i+1]) {
                    return false;
               }
          }
          return true;
     }
     public static boolean isPreorder(String traversal) {
          int[] parts = Arrays.stream(traversal.split("-"))
                              .mapToInt(Integer::parseInt)
                              .toArray();
          return isPreorder(parts, 0, parts.length - 1);
     }

     private static boolean isPreorder(int[] parts, int start, int end) {
          if (start >= end) {
               return true;
          }
          int mid = end + 1;
          int root = parts[start];
          for (int i = start + 1 ; i < end ; i++) {
               if (parts[i] > root) {
                    mid = i;
                    break;
               }
          }
          for (int i = mid ; i < end ; i++) {
               if (parts[i] < root) {
                    return false;
               }
          }
          return isPreorder(parts, start + 1, mid - 1) && isPreorder(parts, mid, end);
          
     }
     public static boolean isPostorder(String traversal) {
          int[] parts = Arrays.stream(traversal.split("-"))
                              .mapToInt(Integer::parseInt)
                              .toArray();
          return isPostorder(parts, 0, parts.length - 1);
     }

     private static boolean isPostorder(int[] parts, int start, int end) {
          if (start >= end) {
               return true;
          }
          int mid = end;
          int root = parts[end];
          for (int i = start ; i < end - 1 ; i++) {
               if (parts[i] > root) {
                    mid = i;
                    break;
               }
          }

          for (int i = mid ; i < end - 1 ; i++) {
               if (parts[i] < root) {
                    return false;
               }
          }
          return isPostorder(parts, start + 1, mid - 1) && isPostorder(parts, mid, end - 1);
     }

     private static boolean isOrdered(String traversal) {
          if (!(isPostorder(traversal) || isInorder(traversal) || isPreorder(traversal))) {
               return false;
          }
          return true;
     }

     public static String verify(String traversal1, String traversal2, String traversal3){
          if (!(isOrdered(traversal1) && isOrdered(traversal2) && isOrdered(traversal3))) {
               return "Invalid traversals";
          } else if (traversal1.length() != traversal2.length() || traversal2.length() != traversal3.length() || traversal1.length() != traversal3.length()) {
               return "Invalid traversals";
          }
          int[] parts1 = Arrays.stream(traversal1.split("-"))
                              .mapToInt(Integer::parseInt)
                              .toArray();
          int[] parts2 = Arrays.stream(traversal2.split("-"))
                              .mapToInt(Integer::parseInt)
                              .toArray();
          int[] parts3 = Arrays.stream(traversal3.split("-"))
                              .mapToInt(Integer::parseInt)
                              .toArray();

          if (isInorder(traversal1)) {
               if (parts2[parts2.length - 1] == parts3[0]) {
                    return "Traversal 1 - Inorder, Traversal 2 - Postorder, Traversal 3 - Preorder"; 
               } else if (parts2[0] == parts3[parts3.length - 1]) {
                    return "Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder"; 
               } else {
                    return "Invalid traversals";
               }
          } else if (isInorder(traversal2)) {
               if (parts1[parts1.length - 1] == parts3[0]) {
                    return "Traversal 1 - Postorder, Traversal 2 - Inorder, Traversal 3 - Preorder"; 
               } else if (parts1[0] == parts3[parts3.length - 1]) {
                    return "Traversal 1 - Preorder, Traversal 2 - Inorder, Traversal 3 - Postorder"; 
               } else {
                    return "Invalid traversals";
               }
          } else if (isInorder(traversal3)) {
               if (parts1[parts1.length - 1] == parts2[0]) {
                    return "Traversal 1 - Postorder, Traversal 2 - Preorder, Traversal 3 - Inorder"; 
               } else if (parts1[0] == parts2[parts2.length - 1]) {
                    return "Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder"; 
               } else {
                    return "Invalid traversals";
               }
          } else {
               return "Invalid traversals";
          }
     }
}