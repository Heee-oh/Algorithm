import java.math.BigInteger;

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        ListNode answer = null;
        BigInteger rs1 = BigInteger.ZERO;
        BigInteger rs2 = BigInteger.ZERO;

        while (l1 != null) {
            sb1.append(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            sb2.append(l2.val);
            l2 = l2.next;
        }

        sb1.reverse();
        sb2.reverse();

        
        rs1 = new BigInteger(sb1.toString());
        sb1.delete(0,sb1.length());

        rs2 = new BigInteger(sb2.toString());

        sb1.append(rs1.add(rs2).toString());

        
        int[] arr = new int[sb1.length()];

        for (int i = 0; i < sb1.length(); i++) {
            arr[i] = sb1.charAt(i) - '0';
        }

        ListNode head = answer;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (answer == null) {
                answer = new ListNode(arr[i]);
                head = answer;

            } else {
                ListNode tmp = new ListNode(arr[i]);
                head.next = tmp;
                head = head.next;

            }
        }


        return answer;

    }
}