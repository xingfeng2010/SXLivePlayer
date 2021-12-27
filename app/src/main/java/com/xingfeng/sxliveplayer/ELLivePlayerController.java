package com.xingfeng.sxliveplayer;

import java.util.List;


import android.util.Log;

import com.xingfeng.sxliveplayer.player.OnInitializedCallback;
import com.xingfeng.sxliveplayer.player.OnStoppedCallback;


public class ELLivePlayerController {

	private static ELLivePlayerController instance = new ELLivePlayerController();
	private ELLivePlayerController(){}
	public static ELLivePlayerController getInstance(){
		return instance;
	}
	
	private ELPlayerController playerController; 
	public ELPlayerController getPlayerController(){
		return playerController;
	}
	private long startTimeMills;
	public long getStartTimeMills(){
		return startTimeMills;
	}
	public void init(String playURL, boolean isUseMediaCodec){
		startTimeMills = System.currentTimeMillis();
		playerController = new ELPlayerController() {
			@Override
			public void showLoadingDialog() {
				super.showLoadingDialog();
			}
			@Override
			public void hideLoadingDialog() {
				super.hideLoadingDialog();
			}
			@Override
			public void onCompletion() {
				super.onCompletion();
				stopPlay(new OnStoppedCallback() {
					@Override
					public void onStopped() {
						Log.i("problem", "正常断开...");
					}
					
					@Override
					public void getstaticsData(long beginOpen, float successOpen, float firstScreenTimeMills,
							float failOpen, int failOpenType, float duration, List<Double> retryOpen,
							List<Double> videoQueueFull, List<Double> videoQueueEmpty) {
						
					}
				});
			}
			@Override
			public void videoDecodeException() {
				super.videoDecodeException();
			}
			@Override
			public void viewStreamMetaCallback(final int width, final int height, float duration) {
				super.viewStreamMetaCallback(width, height, duration);
			}
		};
		playerController.setUseMediaCodec(isUseMediaCodec);
		
		final int probeSize = 50 * 1024;
		playerController.init(playURL, "", new int[] { 1250000, 1750000, 2000000 }, probeSize, true, 2.0f,
				4.0f, new OnInitializedCallback() {
			public void onInitialized(OnInitialStatus onInitialStatus) {
				Log.i("problem", "init onInitialized called initCode " + onInitialStatus);
				switch (onInitialStatus) {
				case CONNECT_FAILED:
					//Retry 必须在新的线程中调用
					new Thread(){
						public void run(){
							String path = "http://mn.maliuedu.com/music/input.mp4";
							playerController.retry(path, "", new int[] { 1250000, 1750000, 2000000 }, probeSize, true, 2.0f,
						4.0f, new OnInitializedCallback(){
								public void onInitialized(OnInitialStatus onInitialStatus) {
									Log.i("problem", "retry onInitialized called initCode " + onInitialStatus);
								}
							});
						}
					}.start();
					break;
				default:
					break;
				}
			}
		});
	}
}
