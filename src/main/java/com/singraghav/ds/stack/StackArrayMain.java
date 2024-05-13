package com.singraghav.ds.stack;

public class StackArrayMain {

    public static void main(String[] args) {
//        MStack<Integer> S = new ArrayStack<>( ); // contents: ()
//        S.push(5); // contents: (5)
//        S.push(3); // contents: (5, 3)
//        System.out.println(S.size( )); // contents: (5, 3) outputs 2
//        System.out.println(S.pop( )); // contents: (5) outputs 3
//        System.out.println(S.isEmpty( )); // contents: (5) outputs false
//        System.out.println(S.pop( )); // contents: () outputs 5
//        System.out.println(S.isEmpty( )); // contents: () outputs true
//        System.out.println(S.pop( )); // contents: () outputs null
//        S.push(7); // contents: (7)
//        S.push(9); // contents: (7, 9)
//        System.out.println(S.top( )); // contents: (7, 9) outputs 9
//        S.push(4); // contents: (7, 9, 4)
//        System.out.println(S.size( )); // contents: (7, 9, 4) outputs 3
//        System.out.println(S.pop( )); // contents: (7, 9) outputs 4
//        S.push(6); // contents: (7, 9, 6)
//        S.push(8); // contents: (7, 9, 6, 8)
//        System.out.println(S.pop( ));

        String html = "<body>\n" +
                "<center>\n" +
                "<h1> The Little Boat </h1>\n" +
                "</center>\n" +
                "<p> The storm tossed the little\n" +
                "boat like a cheap sneaker in an\n" +
                "old washing machine. The three\n" +
                "drunken fishermen were used to\n" +
                "such treatment, of course, but\n" +
                "not the tree salesman, who even as\n" +
                "a stowaway now felt that he\n" +
                "had overpaid for the voyage. </p>\n" +
                "<ol>\n" +
                "<li> Will the salesman die? </li>\n" +
                "<li> What color is the boat? </li>\n" +
                "<li> And what about Naomi? </li>\n" +
                "</ol>\n" +
                "</body>";

        System.out.println(StackQuestions.isHTMLMatched(html));
    }
}
