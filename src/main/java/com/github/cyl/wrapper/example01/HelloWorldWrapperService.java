package com.github.cyl.wrapper.example01;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

public class HelloWorldWrapperService {
	private static final File LOG_FILE = new File("/data/service.log");
	private static boolean RUN_STATUS = true;
	private static boolean STOP_STATUS = false;

	public static void main(String[] args) {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				HelloWorldWrapperService.stop(args);
			}
		});
		start(args);
	}

	public static void start(String[] args) {
		System.out.println("Start HelloWorldWinService !");
		try {
			FileUtils.writeStringToFile(LOG_FILE, "Start HelloWorldWinService !\r\n", true);
			while (RUN_STATUS) {
				FileUtils.writeStringToFile(LOG_FILE, new Date().toString() + "\r\n", true);
				TimeUnit.SECONDS.sleep(5);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		STOP_STATUS = true;
	}

	public static void stop(String[] args) {
		System.out.println("Stop HelloWorldWinService !");
		try {
			FileUtils.writeStringToFile(LOG_FILE, "Stop HelloWorldWinService !\r\n", true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		RUN_STATUS = false;
		while (true) {
			System.out.println("STOP_STATUS:" + STOP_STATUS);
			if (STOP_STATUS) {
				try {
					System.out.println("Stop HelloWorldWinService Successfully!");
					FileUtils.writeStringToFile(LOG_FILE, "Stop HelloWorldWinService Successfully!\r\n", true);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
