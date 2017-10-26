package com.enterprise.common.util;

public class Constants {
	public static final int MAX_FILE_SIZE = Integer.MAX_VALUE;
	
	public static final String UNKNOWN_STR = "Unknown";
	public static final String EMPTY_STR = "";
	public static final String SELECTED_STR = "selected";
	public static final String NULL_STR = "NULL";
	public static final String HTML_SPACE_STR = "&nbsp;";
	public static final String SPACE_STR = " ";
	public static final String MIDNIGHT = "00:00:00";
	public static final String ddMMMyy_FORMAT = "ddMMMyy";							// -> 30AUG11
	public static final String ddMMMyy_FORMAT2 = "dd-MMM-yy";						// -> 30-AUG-11
	public static final String ddMMMyy_FORMAT3 = "dd-MMM-yyyy";
	//added akhil on 24-09-14
	public static final String dMMMyy1_FORMAT = "d-MMM-yy";							// -> 1-AUG-14
	public static final String dMMMyy2_FORMAT = "dMMMyy";							// -> 1AUG14
	public static final String dMMMyy3_FORMAT = "d/MMM/yy";							// -> 1/AUG/14
	public static final String dMMMyy4_FORMAT = "d MMM yy";							// -> 1 AUG 14
	public static final String dMMMyyyy1_FORMAT = "dMMMyyyy";						// -> 1AUG2014
	public static final String dMMMyyyy2_FORMAT = "d/MMM/yyyy";						// -> 1/AUG/2014
	public static final String dMMMyyyy3_FORMAT = "d-MMM-yyyy";						// -> 1-AUG-2014
	public static final String dMMMyyyy4_FORMAT = "d MMM yyyy";						// -> 1 AUG 2014
	public static final String dMyy1_FORMAT = "d/M/yy";								// -> 1/8/14
	public static final String Mdyy1_FORMAT = "M/d/yy";								// -> 1/13/14
	public static final String Mddyy1_FORMAT = "M/dd/yy";							// -> 1/03/14
	public static final String MMddyy1_FORMAT = "MM/dd/yy";							// -> 1/03/14
	public static final String dMyy2_FORMAT = "d-M-yy";								// -> 1-8-14
	public static final String dMyy3_FORMAT = "d M yy";								// -> 1 8 14
	public static final String dMyyyy1_FORMAT = "d/M/yyyy";							// -> 1/8/2014
	public static final String mddyyyy_FORMAT = "M/dd/yyyy";						// -> 4/13/2015
	public static final String mdyyyy_FORMAT = "M/d/yyyy";							// -> 4/9/2015
	public static final String mmddyyyy_FORMAT = "MM/dd/yyyy";						// -> 04/09/2015
	public static final String mmdyyyy_FORMAT = "MM/d/yyyy";						// -> 04/9/2015
	public static final String dMyyyy2_FORMAT = "d-M-yyyy";							// -> 1-8-2014
	public static final String dMyyyy3_FORMAT = "d M yyyy";							// -> 1 8 2014
	public static final String dMMyy1_FORMAT = "d/MM/yy";							// -> 1/08/14
	public static final String dMMyy2_FORMAT = "d MM yy";							// -> 1 08 14
	public static final String dMMyy3_FORMAT = "d-MM-yy";							// -> 1-08-14
	public static final String ddMMyy1_FORMAT = "dd/MM/yy";							// -> 01/08/14
	public static final String ddMMyy2_FORMAT = "dd MM yy";							// -> 01 08 14
	public static final String ddMMyy3_FORMAT = "dd-MM-yy";							// -> 01-08-14
	public static final String ddMyy1_FORMAT = "dd/M/yy";							// -> 01/8/14
	public static final String ddMyy2_FORMAT = "dd M yy";							// -> 01 8 14
	public static final String ddMyy3_FORMAT = "dd-M-yy";							// -> 01-8-14
	public static final String ddMyyyy1_FORMAT = "dd/M/yyyy";						// -> 01/08/2014
	public static final String ddMyyyy2_FORMAT = "dd M yyyy";						// -> 01 08 2014
	public static final String ddMyyyy3_FORMAT = "dd-M-yyyy";						// -> 01-08-2014
	public static final String dMMyyyy1_FORMAT = "d/MM/yyyy";						// -> 1/08/2014
	public static final String dMMyyyy2_FORMAT = "d MM yyyy";						// -> 1 08 2014
	public static final String dMMyyyy3_FORMAT = "d-MM-yyyy";						// -> 1-08-14
	public static final String ddMMyyyy1_FORMAT = "dd/MM/yyyy";						// -> 01/08/2014
	public static final String ddMMyyyy2_FORMAT = "dd MM yyyy";						// -> 01 08 2014
	public static final String ddMMyyyy3_FORMAT = "dd-MM-yyyy";						// -> 01-08-2014
	public static final String ddMMMyy_FORMAT5 = "dd/MMM/yyyy";						// -> 01/AUG/2014
	public static final String ddMMMyy_FORMAT9 = "dd MMM yyyy";						// -> 01 AUG 2014
	public static final String ddMMMyy_FORMAT6 = "ddMMMMMyyyy";						// -> 01AUGUST2014
	public static final String ddMMMyy_FORMAT7 = "dd-MMMMM-yyyy";					// -> 01-AUGUST-2014
	public static final String ddMMMyy_FORMAT8 = "dd/MMMMM/yyyy";					// -> 01/AUGUST/2014
	public static final String dd_MMMMM_yy_FORMAT = "dd MMMMM yyyy";				// -> 30 December 2010
	public static final String yyyyMMdd_FORMAT = "yyyy-MM-dd"; 						// -> 2011-01-30
	public static final String yyyyMMdd_FORMAT2 = "yyyy/MM/dd"; 					// -> 2011/01/30
	public static final String MMM_dd_yy_FORMAT = "MMM-dd-yy"; 						// -> Oct-23-14
	
	public static final String HHmmss_FORMAT = "HH:mm:ss";     						// -> 23:15:00
	
	public static final String HHmmss_FORMAT2 = "HH.mm.ss";     						// -> 23:15:00
	
	public static final String HHmm_FORMAT = "HH:mm"; 	    						// -> 23:15
	
	public static final String hhmmaa_FORMAT = "hh:mm aa";     						// -> 11:20 AM
	
	public static final String yyyyMMdd_FORMAT2_HHmm_FORMAT = "yyyy/MM/dd HH:mm"; 	// -> 2011/01/30
	public static final String yyyyMMdd_FORMAT3_HHmm_FORMAT = "yy-MMM-dd HHmm";	// -> 17-Apr-06 0835
	public static final String yyyyMMdd_FORMAT4_HHmm_FORMAT = "dd/MM/yyyy HH:mm: "; // -> 19/06/2017 13:00:
	public static final String yyyyMMd_FORMAT4_HHmm_FORMAT = "d/MM/yyyy HH:mm: ";
	public static final String yyyyMMd_FORMAT5_HHmm_FORMAT = "d/MM/yyyy HH:mm: ";
	public static final String yyyyMMdd_FORMAT5_HHmmSS_FORMAT = "M/d/yyyy h:mm:ss aa"; // -> 6/12/2017  2:00:00 PM
	
	public static final String yyyyMd_FORMAT5_HHmmSS_FORMAT = "d/M/yyyy h:mm:ss aa";
	public static final String yyyyMMdd_FORMAT6_HHmmSS_FORMAT = "M/d/yyyy H:mm";
	public static final String yyyyMMdd_FORMAT7_HmmSS_FORMAT = "M/dd/yyyy H:mm";
	public static final String yyyyMMdd_FORMAT8_HmmSS_FORMAT = "MM/d/yyyy H:mm";
	public static final String yyyyMMdd_FORMAT8_HmmFORMAT = "MM/dd/yyyy H:mm";
	public static final String yyyyMMdd_FORMAT9_HHmmSS_FORMAT = "d/M/yyyy H:mm";
	public static final String yyyyMMd_FORMAT9_HmmSS_FORMAT = "d/MM/yyyy H:mm";
	public static final String yyyyMMdd_FORMAT9_HmmSS_FORMAT = "dd/M/yyyy H:mm";
	public static final String yyyyMMdd_FORMAT9_HmmFORMAT = "dd/MM/yyyy H:mm";
	public static final String yyyyMMdd_FORMAT10_HmmFORMAT = "dd/MM/yyyy HH:mm";
	public static final String yyyyMMdd_FORMAT13_HmmFORMAT = "MMM d yyyy h:mmaa";
	public static final String yyyyMMdd_FORMAT14_HmmFORMAT = "MMM d yyyy hh:mmaa";
	public static final String yyyyMMdd_FORMAT15_HmmFORMAT = "dd-MM-yyyy HH:mm:";
	public static final String ZERO = "0";
	public static final String ONE = "1";
	public static final String DEFAULT_OPTION = "Select...";
	
	public static final String ERROR_CLASS = "error";
	public static final String ERROR_STYLE = "border:1px solid red;";
	public static final String DEFAULT_STYLE = "border:1px solid #abadb3;";
	
	public static final String ACTIVE_STR = "active";
}