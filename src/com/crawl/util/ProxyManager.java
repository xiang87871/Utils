package com.crawl.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class ProxyManager {

	public static int poolSize = 80;
	static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();
	static LinkedBlockingQueue<String> huishou = new LinkedBlockingQueue<String>();
	public static ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(poolSize);
	public static ExecutorService huishouPool = new ThreadPoolExecutor(20, 50,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(20000));;

	public static int ipSize() {
		return queue.size();
	}

	static ConcurrentHashMap<String, BloomFilter<CharSequence>> map = new ConcurrentHashMap<>();

	public static boolean mightContain(String key, String bloomKey) {
		bloomKey = bloomKey.intern();
		BloomFilter<CharSequence> bloomFilter = map.get(key);
		boolean mightContain = bloomFilter.mightContain(bloomKey);
		if (!mightContain) {
			synchronized (bloomKey) {
				return bloomFilter.mightContain(bloomKey);
			}
		}
		return mightContain;
	}

	public static void addBloom(String key, String bloomKey) {
		bloomKey = bloomKey.intern();
		BloomFilter<CharSequence> bloomFilter = map.get(key);
		synchronized (bloomKey) {
			bloomFilter.put(bloomKey);
		}
	}

	public static void initBloom(String key, List<String> list) {
		BloomFilter<CharSequence> create = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()), 10000000);
		map.put(key, create);
		for (String string : list) {
			create.put(string);
		}
	}

	public static void main(String[] args) {
//		for (String string : queue) {
//			try {
//				String[] split = string.split(":");
//				String doGetWithProxy = HttpClientUtil.doGetWithProxy("http://www.mafengwo.cn/poi/824974.html",
//						new HashMap<String, String>(), split[0], Integer.parseInt(split[1]));
//				if (doGetWithProxy != null && doGetWithProxy.contains("蚂蜂窝")) {
//					System.out.println(string);
//				} else {
//					System.out.println("不行");
//				}
//			} catch (Exception e) {
//
//			}
//
//		}
		System.out.println("开始...");
	}


	public static boolean checkUse(String ip) {
		try {
			String[] split = ip.split(":");
			String doGetWithProxy = HttpClientUtil.doGetWithProxy("http://www.mafengwo.cn/poi/824974.html",
					new HashMap<String, String>(), split[0], Integer.parseInt(split[1]), 1000);
			if (doGetWithProxy != null && doGetWithProxy.contains("蚂蜂窝")) {
				return true;
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return false;
	}

	public static void checkIp(final String mip) {
		huishouPool.execute(new Runnable() {
			@Override
			public void run() {
				if (checkUse(mip)) {
					System.out.println(mip);
					release(mip);
				} else {
					huishou(mip);
				}
			}
		});
	}
	
	static {
		try {
			final File file = new File(ProxyManager.class.getClassLoader().getResource("ips.txt").getFile());
//			@SuppressWarnings("unchecked")
			List<String> readLines = FileUtils.readLines(file,"utf-8");
//			for (String ip : readLines) {
//				final String mip = ip.trim();
//				checkIp(mip);
//			}

			new Timer().schedule(new TimerTask() {
				@Override
				public void run() {
					try {
//						String ip = null;
//						while ((ip = huishou.poll()) != null) {
//							checkIp(ip);
//						}
						
						Thread.sleep(5000);
						if (queue.size() == 0) {
							String doGetSSL = HttpClientUtil.doGet(
									"http://tvp.daxiangdaili.com/ip/?tid=557545515090612&num=1000&category=2&foreign=none&filter=on",
									new HashMap<String, String>());
							if(doGetSSL != null) {
								String[] split = doGetSSL.split("\r\n");
								if(split.length <=1) {
									split = doGetSSL.split("\n");
								}
								
								if(split.length <=1) {
									split = doGetSSL.split("\r");
								}
								
								if(split.length <=1) {
									split = doGetSSL.split("\n\r");
								}
								if(split.length > 1) {
									for (String mip : split) {
										try {
											System.out.println(mip);
											FileUtils.write(file, mip, "utf-8", true);
										} catch(Throwable e) {
											e.printStackTrace();
										}
									}
								}
							}
						}
					} catch (Throwable e) {
						e.printStackTrace();
					}
				}
			}, 0, 30 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String get() {
		try {
			System.out.println("剩余ip: " + queue.size());
			return queue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 释放
	 * @param ip
	 */
	public static void release(String ip) {
		try {
			Thread.sleep(1500);
			queue.put(ip);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 回收
	 * @param ip
	 */
	public static void huishou(String ip) {
		try {
			huishou.put(ip);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
