package org.tcs;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpMethodParams;
import java.io.*;

public class FetchXml
{
  public String parseXML(String departFrom, String departTo, String month) {
	String url = "http://www.kayak.com/h/rss/fare?code="+departFrom+"&dest="+departTo+"&tm=" + month;

	String resultStr = "<table>";
    HttpClient httpClient = new HttpClient();
    GetMethod httpMethod = new GetMethod(url);
    httpMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, 
    		new DefaultHttpMethodRetryHandler(2, false));

    try {
      int httpRespCode = httpClient.executeMethod(httpMethod);

      if (httpRespCode != HttpStatus.SC_OK) {
        System.out.println("Method failed: " + httpMethod.getStatusLine());
      }

      String xmlStr = new String(httpMethod.getResponseBody());
      String tagStr = new String( "<item>" ); 
      int dataInd = xmlStr.indexOf(tagStr,0);

      String tagStr1 = "<link>";
      String tagStr2 = "</link>";
      int begInd = xmlStr.indexOf(tagStr1,dataInd);
      begInd += tagStr1.length();
      int endInd = xmlStr.indexOf(tagStr2,begInd);
      String linkStr = xmlStr.substring(begInd,endInd);
      System.out.println(linkStr);

      tagStr1 = "<description>";
      tagStr2 = "</description>";
      begInd = xmlStr.indexOf(tagStr1,dataInd);
      begInd += tagStr1.length();
      endInd = xmlStr.indexOf(tagStr2,begInd);
      String descStr = xmlStr.substring(begInd,endInd);
      System.out.println(descStr);
	  tagStr1 = "<kyk:departDate>";
      tagStr2 = "</kyk:departDate>";
      begInd = xmlStr.indexOf(tagStr1,dataInd);
      begInd += tagStr1.length();
      endInd = xmlStr.indexOf(tagStr2,begInd);
      String deptDateStr = xmlStr.substring(begInd,endInd);
	  resultStr += "<tr><td>Description:" + descStr + "</td></tr><tr><td>Link: <a href=\"" + linkStr + "\">" + linkStr + "</a></td></tr><tr><td>Departure Date:" + deptDateStr + "</td></tr></table>"; 
	  //System.out.println("The ResultString: " + resultStr);
	  return(resultStr);
    } catch (HttpException e) {
      System.out.println("Internet Error: " + e.getMessage());
      e.printStackTrace();
    } catch (IOException e) {
      System.out.println("Network error: " + e.getMessage());
      e.printStackTrace();
    } finally {
      // Release the connection.
      httpMethod.releaseConnection();
    } 
	return(null);
  }	
}