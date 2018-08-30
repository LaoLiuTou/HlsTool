package com.lt.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ConvertUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConvertHls("test");
		
		
		 
	} 
	
	public static void ConvertHls(String name){
    	
			String base = ConvertUtil.class.getResource("/")
					.getPath();
			 
			try {
				List<String> commend = new java.util.ArrayList<String>();  
				//String ffmpeg_home = base+"tools/ffmpeg/ffmpeg.exe";//ffmpeg.exe所放的路径  
				//String nginx_url = properties.getProperty("nginx_url").trim(); 
				//String inputFile_home = properties.getProperty("inputFile_home").trim(); 
				String inputFile_home = "F:/apache-tomcat-7.0.78/webapps/HlsTool/hls/"; 
	    		String ffmpeg_home = base+"tools/ffmpeg/ffmpeg.exe";//ffmpeg.exe所放的路径  
	    		//String mencoder_home = base+"tools/mencoder/mencoder.exe";//mencoder.exe所放的路径  
				File file = new File(inputFile_home+name);
				if (!file.exists())
				{
					file.mkdir();
				}
				commend.add(ffmpeg_home);  
				//first
		        /*commend.add("-i");
		        commend.add("\""+"rtsp://admin:ld123456@192.168.1.63:554/h264/ch1/main/av_stream"+"\"");
		        commend.add("-vcodec");
                commend.add("copy");
                commend.add("-acodec");
                commend.add("copy");
                commend.add("-vbsf");
                commend.add("h264_mp4toannexb");
		        commend.add("-f");
		        commend.add("hls");
		        commend.add("-hls_time");
		        commend.add("10");
		        commend.add("-hls_list_size");
		        commend.add("0");
		        commend.add("-hls_wrap");
		        commend.add("60");
		        commend.add(inputFile_home+name+"/"+name+".m3u8"); */
		        
				
				//second
                commend.add("-i");    
                //commend.add("rtsp://admin:ld123456@192.168.1.63:554/h264/ch1/main/av_stream");   
                commend.add("E:/wukong.flv");   
                commend.add("-vcodec");
                //commend.add("copy");
                commend.add("libx264");//flv 需解码
                //commend.add("libx264 -s 1920x1080");
                commend.add("-acodec");
                commend.add("copy");
                
                //
                //commend.add("-b:v 1000k -bufsize 1000k");
                
                commend.add("-vbsf");
                commend.add("h264_mp4toannexb");
                commend.add("-hls_time");
                commend.add("10");
                //commend.add("-start_number");
                //commend.add("1");
                commend.add("-f");
                commend.add("segment");
                commend.add("-segment_list");
                commend.add(inputFile_home+name+"/"+name+".m3u8");
                commend.add("-segment_list_flags");
                commend.add("+live");
                commend.add("-segment_wrap");
                commend.add("0");
                
                commend.add("-segment_time");
                commend.add("10");
                commend.add(inputFile_home+name+"/"+name+"%03d.ts");
                
                
                
                //多码率
               //#EXT-X-ENDLIST
                
                
				StringBuffer test=new StringBuffer();    
				for(int i=0;i<commend.size();i++)    
				    test.append(commend.get(i)+" ");    
				System.out.println("转换指令："+test);
				 
			     
	            
	            Runtime rt = Runtime.getRuntime();  
	            Process proc = rt.exec(test.toString());  
	            InputStream stderr = proc.getErrorStream();  
	            InputStreamReader isr = new InputStreamReader(stderr);  
	            BufferedReader br = new BufferedReader(isr);  
	            String line = null;  
	            System.out.println("<LOGGER>");  
	            while ((line = br.readLine()) != null){  
	                System.out.println(line);  
	            }  
	            System.out.println("</LOGGER>");  
	            int exitVal = proc.waitFor();  
	            System.out.println("进程退出状态: " + exitVal);
	          
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
		}

}
