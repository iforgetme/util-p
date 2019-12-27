package compare.po;

import lombok.Data;


/**
 *
 */
//@Setter
//@Getter
@Data
public class Rule {
	
	
	private String start;//截取开始的坐标字符
	private String end;//截取结束的坐标字符
	private String againStart;
	private String againEnd;
	private String [] replaceStr;
	public Rule() {
	}

	public Rule(String start, String end, String againStart, String againEnd) {
		this.start = start;
		this.end = end;
		this.againStart = againStart;
		this.againEnd = againEnd;
	}
}
