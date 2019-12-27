package compare;

import compare.po.Rule;
import lombok.Cleanup;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class copareFileWithTag {
	
	private static  List<Rule> ruleList=new ArrayList<Rule>();//存放规则的集合
	
	
/*	public static void setRule(Rule rule) {
		ruleList.add(rule);
	}
	public static void removeRule(Rule rule) {
		ruleList.remove(rule);
	}
	public static void clearList() {ruleList.clear();}
	
	public static List<Rule> getRuleList(){return ruleList;}*/



	//fmt:message 中的key的值 titleKey的值
	public static void ComPareFile(Path [] compareOnes,Path compareTwo,List<Rule> ruleLists) throws IOException {
		ruleList.addAll(ruleLists);

		for (Path path : compareOnes) {
		@Cleanup	InputStream in = Files.newInputStream(path);
		@Cleanup ByteArrayOutputStream os=new ByteArrayOutputStream();
			byte [] b=new byte[1024];
			while (in.read(b)!=-1) {
				os.write(b);
			}
			String s=os.toString();

		//	System.out.println(s);

		/*	BufferedReader reader = Files.newBufferedReader(path);
			final StringBuffer readLines= new StringBuffer();

			reader.lines().forEach(a->{
				readLines.append(a);
			});
			String s = readLines.toString();

			//获取全文
			String readLine;
			while((readLine=reader.readLine())!=null) {
				readLines+=readLine;
			}*/
		//	System.out.println(readLines);
			List<String> Lines =new ArrayList<>();//要比较的字符串集合
			for (Rule rule : ruleList) {
				String inReadLines=s;
				while (inReadLines.contains(rule.getStart())){

					int start = inReadLines.indexOf(rule.getStart())-1;//截取头
					int end=inReadLines.indexOf(rule.getEnd(),start)+rule.getEnd().length();
					String subString = inReadLines.substring(start, end);
					Lines.add(subString);
					inReadLines=inReadLines.replace(subString,"");
				}
				printNotExist(rule, Lines);
			}
		}
	}

	private static void printNotExist(Rule rule, List<String> splitStr) {
		for (String containsFmtStr : splitStr) {


			//防止 " ' 同时使用
			if(!containsFmtStr.contains(rule.getAgainStart())){
				String[] replaceStr = rule.getReplaceStr();
				for (String s : replaceStr) {
					if(containsFmtStr.contains(s))
						containsFmtStr=containsFmtStr.replaceAll(s,rule.getAgainStart());
				}

			}

			String tag= containsFmtStr.substring(containsFmtStr.indexOf(rule.getAgainStart())+rule.getAgainStart().length(), containsFmtStr.indexOf(rule.getAgainEnd(), containsFmtStr.indexOf(rule.getAgainStart())+rule.getAgainStart().length()));

			boolean existsFile = isExistsFile(tag, Paths.get("C:\\eclispe_workspace\\1104_gbk\\1104_guizhou\\src\\ApplicationResources_zh_CN.properties"));
			if (!existsFile) {
				// System.out.println(tag);
				printLine(tag,Paths.get("C:\\eclispe_workspace\\1104_gbk\\1104_sd\\src\\ApplicationResources_zh_CN.properties"));
			}
			//printLine(tag,Paths.get("C:\\Users\\无语\\Desktop\\贵州1104升级\\中文.txt"));
		}
	}


	/**
     *  判断key是否存在文件中
     * @param key
     * @param path
     * @return 如果存在则返回true  否则返回false
     */
	private static  boolean isExistsFile(String key,Path path){
        boolean flag=false;
        try {
            @Cleanup BufferedReader bufferedReader = Files.newBufferedReader(path);
            Stream<String> lines = bufferedReader.lines();

            flag= lines.anyMatch(s -> s.contains(key));


        } catch (IOException e) {
            e.printStackTrace();
        }


        return flag;
    }


    /**
     * @param key
     * @param path
     */
    private  static  void printLine(String key,Path path){
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            Stream<String> lines = bufferedReader.lines();

            lines.forEach(s ->{
                if (s.contains(key))
                    System.out.println(s);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
	
	
	private static List<String> splitStr(String line) {
		 List<String> resList=new ArrayList<String>();
		 for (Rule rule : ruleList) {
			 while (line.contains(rule.getStart())){

			 	   if (line.equals("window.alert(\"<fmt:message key='balanceMain.errMess.wrongpageobject'/>\");")){
					   System.out.println(line.indexOf(rule.getStart())-1);
					   System.out.println( line.indexOf(rule.getEnd(),line.indexOf(rule.getStart()))+rule.getEnd().length());
				   }
				 System.out.println(line);


					String substring = line.substring(line.indexOf(rule.getStart())-1, line.indexOf(rule.getEnd(),line.indexOf(rule.getStart()))+rule.getEnd().length());
					resList.add(substring);
					//System.out.println(substring);
					line=line.replace(substring, "");

			}
		}
		 
	
		
	/*	 String [] resArr =new String [resList.size()];
		 for (int i=0;i<resList.size();i++) {
			 resArr[i]=resList.get(i);
		}*/
		return resList;
	}
	
	
	public static void main(String[] args) {
		Path [] paths= {Paths.get("C:\\eclispe_workspace\\1104_gbk\\1104_guizhou\\web\\acrossdatacheck\\acrossdatacheckReportSearch.jsp"),
                Paths.get("C:\\eclispe_workspace\\1104_gbk\\1104_guizhou\\web\\acrossdatacheck\\acrossdatacheckForm.jsp")
				};

		List<Rule> ruleLists=new ArrayList<>();
		Rule rule = new Rule("fmt:message", "/>", "key=\"", "\"");
        rule.setReplaceStr(new String[]{"\"","'"});
		Rule rule1 = new Rule("fmt:message", "/>", "key='", "'");
		rule1.setReplaceStr(new String[]{"\"","'"});
		ruleLists.add(rule);
		ruleLists.add(rule1);


		try {
			ComPareFile(paths, null,ruleLists);
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*String aa="var str='<font color=\"blue\" >'+'<fmt:message key=\"bbjs.tipinfo1\" />'+'<br/>'+'<fmt:message key=\"bbjs.tipinfo2\" />'+'<br/>'+'<fmt:message key=\"bbjs.tipinfo3\" />'+'<br/>'+'<fmt:message key=\"bbjs.tipinfo4\" /></font>';";
		splitStr(aa);*/
	}
	
	
	

}
