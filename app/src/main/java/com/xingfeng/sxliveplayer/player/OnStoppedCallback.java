package com.xingfeng.sxliveplayer.player;

import java.util.List;

public interface OnStoppedCallback {
	public void onStopped();
	
	//观看结束打点数据
	/**
	 * @param beginOpen 开始试图去打开一个直播流
	 * @param successOpen 成功打开流
	 * @param firstScreenTimeMills 首屏时间
	 * @param failOpen 流打开失败
	 * @param failOpenType 流打开失败类型
	 * @param duration 时长
	 * @param retryOpen 重试
	 * @param videoQueueFull 解码缓冲区满
	 * @param videoQueueEmpty 解码缓冲区空
	 */
	public void getstaticsData(long beginOpen, float successOpen, float firstScreenTimeMills, float failOpen, int failOpenType, float duration,
                               List<Double> retryOpen, List<Double> videoQueueFull, List<Double> videoQueueEmpty);
}

