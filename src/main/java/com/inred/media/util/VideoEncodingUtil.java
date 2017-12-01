package com.inred.media.util;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;
import it.sauronsoftware.jave.MultimediaInfo;
import it.sauronsoftware.jave.VideoAttributes;
import it.sauronsoftware.jave.VideoSize;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VideoEncodingUtil {
	private static final Logger LOG = LoggerFactory
			.getLogger(VideoEncodingUtil.class);
	


	public static void encodingToMp4(String sourcePath, String targetPath) {

		File source = new File(sourcePath);
		File target = new File(targetPath);
		AudioAttributes audio = new AudioAttributes();
		audio.setCodec("libmp3lame");
		audio.setBitRate(new Integer(64000));
		audio.setChannels(new Integer(1));
		audio.setSamplingRate(new Integer(22050));
		VideoAttributes video = new VideoAttributes();
		video.setCodec("libxvid");
		video.setBitRate(new Integer(1000000));//越大越清楚
		video.setFrameRate(new Integer(1));
		video.setSize(new VideoSize(600, 500));
		EncodingAttributes attrs = new EncodingAttributes();
		attrs.setFormat("mp4");
		attrs.setAudioAttributes(audio);
		attrs.setVideoAttributes(video);
		Encoder encoder = new Encoder();
		try {
			long beginTime = System.currentTimeMillis();
			encoder.encode(source, target, attrs);
			LOG.info("视频转码花费时间是："
					+ (System.currentTimeMillis() - beginTime));
		} catch (IllegalArgumentException e) {
			LOG.error(e.getMessage(), e);
		} catch (InputFormatException e) {
			LOG.error(e.getMessage(), e);
		} catch (EncoderException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public static void encodingToFlv(String sourcePath, String targetPath) {
		File source = new File(sourcePath);
		File target = new File(targetPath);
		AudioAttributes audio = new AudioAttributes();
		audio.setCodec("libmp3lame");
		audio.setBitRate(new Integer(64000));
		audio.setChannels(new Integer(1));
		audio.setSamplingRate(new Integer(22050));
		VideoAttributes video = new VideoAttributes();
		video.setCodec("flv");
		video.setBitRate(new Integer(1000000));
		video.setFrameRate(new Integer(8));//越小越清析
		video.setSize(new VideoSize(600, 500));
		EncodingAttributes attrs = new EncodingAttributes();
		attrs.setFormat("flv");
		attrs.setAudioAttributes(audio);
		attrs.setVideoAttributes(video);
		Encoder encoder = new Encoder();

		try {
			long beginTime = System.currentTimeMillis();
			encoder.encode(source, target, attrs);
			LOG.info("视频转码花费时间是："
					+ (System.currentTimeMillis() - beginTime));
		} catch (IllegalArgumentException e) {
			LOG.error(e.getMessage(), e);
		} catch (InputFormatException e) {
			LOG.error(e.getMessage(), e);
		} catch (EncoderException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	public static void methodpng(String sourcePath, String targetPath) {
		File source = new File(sourcePath);
		File target = new File(targetPath);// 转图片
		VideoAttributes video = new VideoAttributes();
		video.setCodec("png");// 转图片
		video.setSize(new VideoSize(600, 500));
		EncodingAttributes attrs = new EncodingAttributes();
		attrs.setFormat("image2");// 转图片
		attrs.setOffset(3f);// 设置偏移位置，即开始转码位置（3秒）
		attrs.setDuration(0.01f);// 设置转码持续时间（1秒）
		attrs.setVideoAttributes(video);
		Encoder encoder = new Encoder();
		try{
		long beginTime = System.currentTimeMillis();
		encoder.encode(source, target, attrs);
		LOG.info("视频转码花费时间是："
				+ (System.currentTimeMillis() - beginTime));
		
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InputFormatException e) {
			e.printStackTrace();
		} catch (EncoderException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 将.wav转换成mp3
	 * @param sourcePath
	 * @param targetPath
	 */
	public static void encodingToMp3(String sourcePath, String targetPath) {
		File source = new File(sourcePath);  
		File target = new File(targetPath);  
		AudioAttributes audio = new AudioAttributes();  
		audio.setCodec("libmp3lame");  
		audio.setBitRate(new Integer(128000));  
		audio.setChannels(new Integer(1));  
		audio.setSamplingRate(new Integer(44100));  
		EncodingAttributes attrs = new EncodingAttributes();  
		attrs.setFormat("mp3");  
		attrs.setAudioAttributes(audio);  
		Encoder encoder = new Encoder();  
		try {
			long beginTime = System.currentTimeMillis();
			encoder.encode(source, target, attrs);
			LOG.info("视频转码花费时间是："
					+ (System.currentTimeMillis() - beginTime));
		} catch (IllegalArgumentException e) {
			LOG.error(e.getMessage(), e);
		} catch (InputFormatException e) {
			LOG.error(e.getMessage(), e);
		} catch (EncoderException e) {
			LOG.error(e.getMessage(), e);
		}
	}
 
	

	public static void main(String[] args) {
		//encodingToFlv("E:/test/ff.flv","E:/test/222222.flv");
		//encodingToMp4("E:/test/5.wmv","E:/test/jjjj.mp4");
		encodingToMp3("/Flash/11.mp3","/Flash/22.mp3");
		
	}

}
