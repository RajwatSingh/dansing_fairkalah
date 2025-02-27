/** 
 * Stopwatch - A simple millisecond stopwatch.  
 * start() sets stopwatch measure of time elapsed.
 * stop() adds time elapsed since previous start() to total elapsed time and returns total elapsed time.
 * reset() resets total elapsed time.
 * lap() returns total elapsed time without "stopping" the stopwatch.
 * It's actually easier to read the code than these comments. :)
 * @author Todd W. Neller
 */

public class Stopwatch {
	private long totalMillis = 0;
	private long startMillis = 0;
	private long stopMillis = 0;

	/**
	 * Return millisecond stopwatch time elapsed so far.
	 * @return long
	 */
	public long lap() {
		return (System.currentTimeMillis() - startMillis) + totalMillis;
	}

	/**
	 * Reset the millisecond stopwatch.
	 */
	public void reset() {
		totalMillis = startMillis = stopMillis = 0; 
	}

	/**
	 * Start millisecond stopwatch.
	 */
	public void start() {
		startMillis = System.currentTimeMillis();
	}

	/**
	 * Stop millisecond stopwatch and return total time elapsed.
	 * @return long
	 */
	public long stop() {
		stopMillis = System.currentTimeMillis();
		totalMillis += stopMillis - startMillis;
		startMillis = stopMillis = 0;
		return totalMillis;
	}

}
