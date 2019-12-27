package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description  regex
 * Create by  无语
 * Date on  2019/9/26 10:32
 */
public class demo {



//(?<old>\s\S*)\]\](?<return>\s\S*)$



    public static void main(String[] args) {
        String a="[split0_[C3136436000019][cams00100101][2019091611020912112]][C3136436000019][cams00100101][2019091611461683406]rr.txt";
        Pattern compile = Pattern.compile("(?<remove>\\[{1}[^\\]]*\\[{1})(?<repFileName>[\\s\\S]*)\\]\\](?<returnFileName>[\\s\\S]*)");
        Matcher matcher = compile.matcher(a);
        System.out.println( matcher.find());
        int i = matcher.groupCount();
        System.out.println(i);
        String remove = matcher.group(1);
       String old = matcher.group(2);
        String returnS = matcher.group(3);
        System.out.println(remove);
        System.out.println("["+old+"]");
        System.out.println(returnS);

    }

}
