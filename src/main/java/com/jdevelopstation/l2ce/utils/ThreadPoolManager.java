package com.jdevelopstation.l2ce.utils;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Author: VISTALL
 * Company: J Develop Station
 * Date: 04/01/2010
 * Time: 9:34:38
 */
public class ThreadPoolManager
{
	private static ThreadPoolManager _instance = new ThreadPoolManager();
	private ScheduledThreadPoolExecutor _pool;

	public static ThreadPoolManager getInstance()
	{
		return _instance;
	}

	ThreadPoolManager()
	{
		_pool = new ScheduledThreadPoolExecutor(4, new PriorityThreadFactory("ThreadPoolManager", Thread.NORM_PRIORITY));
		scheduleAtFixedRate(new RunnableImpl()
		{
			@Override
			public void runImpl()
			{
				_pool.purge();
			}
		}, 10000L, 10000L);
	}

	public void execute(RunnableImpl r)
	{
		_pool.execute(r);
	}

	public ScheduledFuture scheduleAtFixedRate(RunnableImpl r, long initial, long delay)
	{
		try
		{
			if (delay < 0)
			{
				delay = 0;
			}
			if (initial < 0)
			{
				initial = 0;
			}
			return _pool.scheduleAtFixedRate(r, initial, delay, TimeUnit.MILLISECONDS);
		}
		catch (RejectedExecutionException e)
		{
			return null; /* shutdown, ignore */
		}
	}

	public void shutdown()
	{
		_pool.shutdown();
	}
}
