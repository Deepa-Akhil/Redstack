package com.enterprise.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DateUtils {
	
	private static GetProperty getPropertyStat;
	
	@Autowired
	GetProperty getProperty;
	
	@SuppressWarnings({ "unused", "static-access" })
	@PostConstruct     
	private void init() {
		this.getPropertyStat = this.getProperty;
	}
	
	public static Date toValidatedDate1(String dateAsStr) throws RuntimeException {
		if (!StringUtils.isEmpty(dateAsStr)) {
			dateAsStr = dateAsStr.replaceAll("  ", " ").trim();
			Date date = null;
			date = DateUtils.toValidatedDate(dateAsStr, Constants.dMMyyyy1_FORMAT);
			if (date == null) {
				date = DateUtils.toValidatedDate(dateAsStr, Constants.ddMMMyy_FORMAT);
				if (date == null) {
					date = DateUtils.toValidatedDate(dateAsStr, Constants.ddMMMyy_FORMAT2);
					if (date == null) {
						date = DateUtils.toValidatedDate(dateAsStr, Constants.ddMMMyy_FORMAT3);
						if (date == null) {
							date = DateUtils.toValidatedDate(dateAsStr, Constants.yyyyMMdd_FORMAT);
							if (date == null) {
								date = DateUtils.toValidatedDate(dateAsStr, Constants.yyyyMMdd_FORMAT2);
								if (date == null) {
									date = DateUtils.toValidatedDate(dateAsStr, Constants.dMMMyy1_FORMAT);
									if (date == null) {
										date = DateUtils.toValidatedDate(dateAsStr, Constants.dMMMyy2_FORMAT);
										if (date == null) {
											date = DateUtils.toValidatedDate(dateAsStr, Constants.dMMMyy3_FORMAT);
											if (date == null) {
												date = DateUtils.toValidatedDate(dateAsStr, Constants.dMMMyy4_FORMAT);
												if (date == null) {
													date = DateUtils.toValidatedDate(dateAsStr, Constants.dMyy1_FORMAT);
													if (date == null) {
														date = DateUtils.toValidatedDate(dateAsStr, Constants.dMyy2_FORMAT);
														if (date == null) {
															date = DateUtils.toValidatedDate(dateAsStr, Constants.dMyy3_FORMAT);
															if (date == null) {
																date = DateUtils.toValidatedDate(dateAsStr, Constants.mdyyyy_FORMAT);
																if (date == null) {
																	date = DateUtils.toValidatedDate(dateAsStr, Constants.dMyyyy1_FORMAT);
																	if (date == null) {
																		date = DateUtils.toValidatedDate(dateAsStr, Constants.dMyyyy2_FORMAT);
																		if (date == null) {
																			date = DateUtils.toValidatedDate(dateAsStr, Constants.dMyyyy3_FORMAT);
																			if (date == null) {
																				date = DateUtils.toValidatedDate(dateAsStr, Constants.dMMMyyyy1_FORMAT);
																				if (date == null) {
																					date = DateUtils.toValidatedDate(dateAsStr, Constants.dMMMyyyy2_FORMAT);
																					if (date == null) {
																						date = DateUtils.toValidatedDate(dateAsStr, Constants.dMMMyyyy3_FORMAT);
																						if (date == null) {
																							date = DateUtils.toValidatedDate(dateAsStr, Constants.dMMMyyyy4_FORMAT);
																							if (date == null) {
																								date = DateUtils.toValidatedDate(dateAsStr, Constants.dMMyy1_FORMAT);
																								if (date == null) {
																									date = DateUtils.toValidatedDate(dateAsStr, Constants.dMMyy2_FORMAT);
																									if (date == null) {
																										date = DateUtils.toValidatedDate(dateAsStr, Constants.dMMyy3_FORMAT);
																										if (date == null) {
																											date = DateUtils.toValidatedDate(dateAsStr, Constants.ddMMyy1_FORMAT);
																											if (date == null) {
																												date = DateUtils.toValidatedDate(dateAsStr, Constants.ddMMyy2_FORMAT);
																												if (date == null) {
																													date = DateUtils.toValidatedDate(dateAsStr, Constants.ddMMyy3_FORMAT);
																													if (date == null) {
																														date = DateUtils.toValidatedDate(dateAsStr, Constants.dMMyyyy2_FORMAT);
																														if (date == null) {
																															date = DateUtils.toValidatedDate(dateAsStr, Constants.dMMyyyy3_FORMAT);
																															if (date == null) {
																																date = DateUtils.toValidatedDate(dateAsStr, Constants.ddMMyyyy1_FORMAT);
																																if (date == null) {
																																	date = DateUtils.toValidatedDate(dateAsStr, Constants.ddMMyyyy2_FORMAT);
																																	if (date == null) {
																																		date = DateUtils.toValidatedDate(dateAsStr, Constants.ddMMyyyy3_FORMAT);
																																		if (date == null) {
																																			date = DateUtils.toValidatedDate(dateAsStr, Constants.ddMyy1_FORMAT);
																																			if (date == null) {
																																				date = DateUtils.toValidatedDate(dateAsStr, Constants.ddMyy2_FORMAT);
																																				if (date == null) {
																																					date = DateUtils.toValidatedDate(dateAsStr, Constants.ddMyy3_FORMAT);
																																					if (date == null) {
																																						date = DateUtils.toValidatedDate(dateAsStr, Constants.ddMyyyy1_FORMAT);
																																						if (date == null) {
																																							date = DateUtils.toValidatedDate(dateAsStr, Constants.ddMyyyy2_FORMAT);
																																							if (date == null) {
																																								date = DateUtils.toValidatedDate(dateAsStr, Constants.ddMyyyy3_FORMAT);
																																								if (date == null) {
																																									date = DateUtils.toValidatedDate(dateAsStr, Constants.ddMMMyy_FORMAT5);
																																									if (date == null) {
																																										date = DateUtils.toValidatedDate(dateAsStr, Constants.ddMMMyy_FORMAT6);
																																										if (date == null) {
																																											date = DateUtils.toValidatedDate(dateAsStr, Constants.ddMMMyy_FORMAT7);
																																											if (date == null) {
																																												date = DateUtils.toValidatedDate(dateAsStr, Constants.ddMMMyy_FORMAT8);
																																												if (date == null) {
																																													date = DateUtils.toValidatedDate(dateAsStr, Constants.ddMMMyy_FORMAT9);
																																													if (date == null) {
																																														date = DateUtils.toValidatedDate(dateAsStr, Constants.dd_MMMMM_yy_FORMAT);
																																														if (date == null) {
																																															date = DateUtils.toValidatedDate(dateAsStr, Constants.MMM_dd_yy_FORMAT);
																																															if (date == null) {
																																																date = DateUtils.toValidatedDate(dateAsStr, Constants.mddyyyy_FORMAT);
																																																if (date == null) {
																																																	date = DateUtils.toValidatedDate(dateAsStr, Constants.yyyyMMdd_FORMAT10_HmmFORMAT);
																																																	if (date == null) {
																																																		date = DateUtils.toValidatedDate(dateAsStr, Constants.yyyyMMdd_FORMAT4_HHmm_FORMAT);
																																																		if (date == null) {
																																																			date = DateUtils.toValidatedDate(dateAsStr, Constants.yyyyMMdd_FORMAT5_HHmmSS_FORMAT);
																																																			if (date == null) {
																																																				date = DateUtils.toValidatedDate(dateAsStr, Constants.yyyyMMd_FORMAT4_HHmm_FORMAT);
																																																				if (date == null) {
																																																					date = DateUtils.toValidatedDate(dateAsStr, Constants.yyyyMMd_FORMAT5_HHmm_FORMAT);
																																																					if (date == null) {
																																																						date = DateUtils.toValidatedDate(dateAsStr, Constants.yyyyMMdd_FORMAT6_HHmmSS_FORMAT);
																																																						if (date == null) {
																																																							date = DateUtils.toValidatedDate(dateAsStr, Constants.yyyyMMdd_FORMAT7_HmmSS_FORMAT);
																																																							if (date == null) {
																																																								date = DateUtils.toValidatedDate(dateAsStr, Constants.yyyyMMdd_FORMAT8_HmmSS_FORMAT);
																																																								if (date == null) {
																																																									date = DateUtils.toValidatedDate(dateAsStr, Constants.yyyyMMdd_FORMAT3_HHmm_FORMAT);
																																																									if (date == null) {
																																																										date = DateUtils.toValidatedDate(dateAsStr, Constants.yyyyMMdd_FORMAT9_HHmmSS_FORMAT);
																																																										if (date == null) {
																																																											date = DateUtils.toValidatedDate(dateAsStr, Constants.yyyyMMd_FORMAT9_HmmSS_FORMAT);
																																																											if (date == null) {
																																																												date = DateUtils.toValidatedDate(dateAsStr, Constants.yyyyMMdd_FORMAT9_HmmSS_FORMAT);
																																																												if (date == null) {
																																																													date = DateUtils.toValidatedDate(dateAsStr, Constants.yyyyMMdd_FORMAT9_HmmFORMAT);
																																																													if (date == null) {
																																																														date = DateUtils.toValidatedDate(dateAsStr, Constants.Mdyy1_FORMAT);
																																																														if (date == null) {
																																																															date = DateUtils.toValidatedDate(dateAsStr, Constants.Mddyy1_FORMAT);
																																																															if (date == null) {
																																																																date = DateUtils.toValidatedDate(dateAsStr, Constants.MMddyy1_FORMAT);
																																																																if (date == null) {
																																																																	date = DateUtils.toValidatedDate(dateAsStr, Constants.yyyyMMdd_FORMAT8_HmmFORMAT);
																																																																	if (date == null) {
																																																																		date = DateUtils.toValidatedDate(dateAsStr, Constants.yyyyMMdd_FORMAT13_HmmFORMAT);
																																																																		if (date == null) {
																																																																			date = DateUtils.toValidatedDate(dateAsStr, Constants.yyyyMMdd_FORMAT15_HmmFORMAT);
																																																																			if (date == null)
																																																																				throw new RuntimeException();
																																																																		}
																																																																	}
																																																																}
																																																															}
																																																														}
																																																													}
																																																												}
																																																											}
																																																										}
																																																									}
																																																								}
																																																							}
																																																						}
																																																					}
																																																				}
																																																			}
																																																		}
																																																	}
																																																}
																																															}
																																														}
																																													}
																																												}
																																											}
																																										}
																																									}
																																								}
																																							}
																																						}
																																					}
																																				}
																																			}
																																		}
																																	}
																																}
																															}
																														}
																													}
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			return date;
		} else
			return null;
	}
	
	public static Date toValidatedDate(String dateAsStr) throws RuntimeException {
		if (!StringUtils.isEmpty(dateAsStr)) {
			dateAsStr = dateAsStr.replaceAll("  ", " ");
			Date date = null;
			for (String dateFrmt : getPropertyStat.getProperty("date.formats").split(",")) {
				date = DateUtils.toValidatedDate(dateAsStr.trim(), dateFrmt.trim());
				if(date != null)
					break;
			}
			if (date == null)
				throw new RuntimeException();
			return date;
		} else
			return null;
	}
	
	public static java.util.Date newDate() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(Constants.yyyyMMdd_FORMAT2 + " " + Constants.HHmmss_FORMAT);
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+02:00"));
		String formattedDateStr = formatter.format(date);
		Date formattedDate = null;
		try {
			SimpleDateFormat formatter2 = new SimpleDateFormat(Constants.yyyyMMdd_FORMAT2 + " " + Constants.HHmmss_FORMAT);
			formattedDate = formatter2.parse(formattedDateStr);
		} catch (ParseException e) {
			formattedDate = date;
		}
		return formattedDate;
	}
	
	public static java.util.Date calendarToDate(Calendar cal) {
		//Create instance of java.util.Date
	    java.util.Date utilDate = cal.getTime();
	    return utilDate;
	}
	
	public static Calendar stringToCalendar(String iYear, String iMonth, String iDate) {
		int year  = Integer.parseInt(iYear);
	    int month = Integer.parseInt(iMonth) - 1;  //int month = 0; //January
	    int date  = Integer.parseInt(iDate);
	    //calendar
	    Calendar cal = new GregorianCalendar(year, month, date);
	    //Clear all fields
	    cal.clear();
	    cal.set(Calendar.YEAR, year);
	    cal.set(Calendar.MONTH, month);
	    cal.set(Calendar.DATE, date);
	    //done
	    return cal;
	}
	
	/**
     * Converts a java.util.Date into an instance of XMLGregorianCalendar
     *
     * @param date Instance of java.util.Date or a null reference
     * @return XMLGregorianCalendar instance whose value is based upon the
     *  value in the date parameter. If the date parameter is null then
     *  this method will simply return null.
     */
    public static XMLGregorianCalendar asXMLGregorianCalendar(java.util.Date date) {
        if (date == null) {
            return null;
        } else {
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTimeInMillis(date.getTime());
            DatatypeFactory nf;
			try {
				nf = DatatypeFactory.newInstance();
				return nf.newXMLGregorianCalendar(gc);
			} catch (DatatypeConfigurationException e) {
				e.printStackTrace();
			}
            return null;
        }
    }

    /**
     * Converts an XMLGregorianCalendar to an instance of java.util.Date
     *
     * @param xgc Instance of XMLGregorianCalendar or a null reference
     * @return java.util.Date instance whose value is based upon the
     *  value in the xgc parameter. If the xgc parameter is null then
     *  this method will simply return null.
     */
    public static java.util.Date asDate(XMLGregorianCalendar xgc) {
        if (xgc == null) {
            return null;
        } else {
            return xgc.toGregorianCalendar().getTime();
        }
    }
    	
	public static boolean validateDate(final String dateAsStr, final String dateFormatAsStr) {
		boolean valid = true;
		DateFormat dateFormat = new SimpleDateFormat(dateFormatAsStr);
		Date date = null;
		try {
			date = dateFormat.parse(dateAsStr);
			// dateformat.parse will accept any date as long as it's in the format
		    // you defined, it simply rolls dates over, for example, december 32
		    // becomes jan 1 and december 0 becomes november 30
		    // This statement will make sure that once the string
		    // has been checked for proper formatting that the date is still the
		    // date that was entered, if it's not, we assume that the date is invalid
			String parsedDateAsStr = dateFormat.format(date).toUpperCase();
		    if (!parsedDateAsStr.equals(dateAsStr))
		    	valid = false;
		} catch (Throwable e) {
			valid = false;
		}
		return valid;
	}
	
	public static Date toValidatedDate(final String dateAsStr, final String dateFormatAsStr) {
		DateFormat dateFormat = new SimpleDateFormat(dateFormatAsStr);
		Date date = null;
		try {
			date = dateFormat.parse(dateAsStr);
			// dateformat.parse will accept any date as long as it's in the format
		    // you defined, it simply rolls dates over, for example, december 32
		    // becomes jan 1 and december 0 becomes november 30
		    // This statement will make sure that once the string
		    // has been checked for proper formatting that the date is still the
		    // date that was entered, if it's not, we assume that the date is invalid
			String parsedDateAsStr = dateFormat.format(date).toUpperCase();
		    if (!parsedDateAsStr.equals(dateAsStr))
		    	return null;
		} catch (Throwable e) {
			return null;
		}
		return date;
	}
	
	public static String formatForDBTime(String time) {
		if (!StringUtils.isEmpty(time)) {
			if (time.length() == 5)
				time = time + ":00";
			return time;
		} else
			return null;
	}
	
	public static Date toDate(String dateAsStr, String dateFormat, String timeAsStr, String timeFormat) {
		Date date = null;
		String dateTimeFormat = dateFormat + " " + timeFormat;
		SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(dateTimeFormat);
		String dateTimeAsStr = dateAsStr + " " + DateUtils.formatForDBTime(timeAsStr);
		try {
			date = dateTimeFormatter.parse(dateTimeAsStr);
		} catch (ParseException e) {
			System.out.println("WARNING: Date and time [" + dateTimeAsStr + "] could not be parsed to java.util.Date: " + e.getMessage());
		}
		return date;
	}
	
	public static Date toDate(String dateAsStr, String dateFormat) {
		Date date = null;
		SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);
		try {
			date = dateFormatter.parse(dateAsStr);
		} catch (ParseException e) {
			System.out.println("WARNING: Date [" + dateAsStr + "] could not be parsed to java.util.Date: " + e.getMessage());
		}
		return date;
	}
	
	public static String toString(Date date, String dateFormat) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);
		String dateStr = null;
		try {
			dateStr = dateFormatter.format(date);
			dateStr = dateStr.toUpperCase();
		} catch (Exception e) {
			if (date != null) {
				System.out.println("WARNING: Date [" + date.toString() + "] could not be formatted to String: " + e.getMessage());
			}
		}
		return dateStr;
	}
	
	public static String formatDate(Date date, String dateFormat, String timeFormat) {
		String formattedDate = Constants.EMPTY_STR;
		if (date != null) {
			String fullFormat = null;
			if (!StringUtils.isEmpty(dateFormat)) {
				fullFormat = dateFormat;
				if (!StringUtils.isEmpty(timeFormat)) {
					fullFormat = fullFormat + " " + timeFormat;
				}
			} else {
				if (!StringUtils.isEmpty(timeFormat)) {
					fullFormat = timeFormat;
				}
			}
			SimpleDateFormat formatter = new SimpleDateFormat(fullFormat);
			formattedDate = formatter.format(date);
		}
		return formattedDate;
	}
		
	public static int minutesBetweenDates(Date endDate, Date startDate) {
		long minutesBetween = 0L;
		if (endDate != null && startDate != null) {
			long endDateMillis = endDate.getTime();
			long startDateMillis = startDate.getTime();
			long millisBetween = endDateMillis - startDateMillis;
			millisBetween = Math.abs(millisBetween);
			if (millisBetween > 0L) {
				long secondsBetween = millisBetween / 1000;
				if (secondsBetween > 0L) {
					minutesBetween = secondsBetween / 60;
				}
			}
		}
		return Long.valueOf(minutesBetween).intValue();
	}
	
	/**
	 * Method to convert the file date in the form: dd/MM/yyyy or 30/01/2011 to a java util date.
	 * @param fileDate
	 * @return java.util.Date
	 */
	public static Date constructDateFromFileDateStr(String fileDateStr, String delimeter, String dateFormat) throws Exception {
		if (StringUtils.isEmpty(fileDateStr)) {
			throw new Exception("Invalid date [" + fileDateStr + "]");
		} else {
			StringTokenizer dateTokenizer = new StringTokenizer(fileDateStr, "/");
			if (dateTokenizer != null && dateTokenizer.countTokens() == 3) {
				int counter = 0;
				String year = null;
				String month = null;
				String date = null;
				while (dateTokenizer.hasMoreTokens()) {
					String value = dateTokenizer.nextToken();
					switch (counter) {
						case 0: {
							date = value; 
							if (date.length() != 2) {
								date = "0" + date;
							}
							break;
						}
						case 1: {
							month = value; 
							if (month.length() != 2) {
								month = "0" + month;
							}
							break;
						}
						case 2: {
							year = value; break;
						}
					}
					counter++;
				}
				fileDateStr = date + delimeter + month + delimeter + year;
				/*Calendar cal = DateUtils.stringToCalendar(year, month, date);
				utilDate = DateUtils.calendarToDate(cal);*/
				DateFormat dateFormatter = new SimpleDateFormat(dateFormat);
				Date parsedDate = dateFormatter.parse(fileDateStr);
				// dateformat.parse will accept any date as long as it's in the format
			    // you defined, it simply rolls dates over, for example, december 32
			    // becomes jan 1 and december 0 becomes november 30
			    // This statement will make sure that once the string
			    // has been checked for proper formatting that the date is still the
			    // date that was entered, if it's not, we assume that the date is invalid
				String parsedDateAsStr = dateFormatter.format(parsedDate).toUpperCase();
			    if (!parsedDateAsStr.equals(fileDateStr)) {
			    	throw new Exception("Invalid date [" + fileDateStr + "]");
			    }
				return parsedDate;
			} else {
				throw new Exception("Calendar date could not be instantiated by Date string [" + fileDateStr + "]");
			}
		}
	}
	
	public static boolean isValidPeriod(Date startDate, Date endDate){
		boolean isValid = true;
		long diff = endDate.getTime() - startDate.getTime();
		if(diff<0) {
			isValid = false;
		}
		return isValid;
	}
	
	public static boolean isThisDateWithinRange(Date dateToValidate, Date startDate, Date endDate) {
		try {
			if ((dateToValidate.before(endDate) || dateToValidate.equals(endDate))
					&& (dateToValidate.after(startDate) || dateToValidate.equals(startDate))) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean isThisPeriodWithinRange(Date extStrtDt, Date extEndDt, Date fileStrtDt, Date fileEndDt) {
		try {
			if((fileStrtDt.before(extStrtDt)  || fileStrtDt.equals(extStrtDt)) && 
					(fileEndDt.after(extEndDt) || fileEndDt.equals(extEndDt))) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static Date dateMinusOne(Date date){
		return new DateTime(date).minusDays(1).toDate();
	}
	
	public static Date datePlusOne(Date date){
		return new DateTime(date).plusDays(1).toDate();
	}
	
	public static void main(String[] args) {
		/*Date startDate = DateUtils.toValidatedDate(StringUtils.upper("6/7/2017 0:00"), "M/d/yyyy H:mm");
		System.out.println("----------"+startDate);*/
		/*Date endDate = DateUtils.toValidatedDate(StringUtils.upper("31/5/2016"));
		Date dateToValidate1 = DateUtils.toValidatedDate(StringUtils.upper("18-Aug-15"));
		Date dateToValidate2 = DateUtils.toValidatedDate(StringUtils.upper("1-Oct-15"));
		//boolean minusOneDate = DateUtils.isThisPeriodWithinRange(startDate, endDate, dateToValidate1, dateToValidate2);
		boolean minusOneDate = DateUtils.isValidPeriod(startDate, endDate);*/
		System.out.println("----------"+DateUtils.toValidatedDate1(StringUtils.upper("19-10-2017 07:00")));
	}
}