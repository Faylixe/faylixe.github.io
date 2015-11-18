package review.classdesign.sample;

import java.awt.event.ActionEvent;
import java.util.function.Consumer;

/**
 * Application controller interface.
 * 
 * @author fv
 */
public interface Controller extends Consumer<Runnable> {

	/**
	 * Action listener for <tt>Open</tt> action.
	 * 
	 * @param event Event that trigger this method call.
	 */
	void open(ActionEvent event);
	
	/**
	 * Action listener for <tt>Save</tt> action.
	 * 
	 * @param event Event that trigger this method call.
	 */
	void save(ActionEvent event);

	/**
	 * Action listener for <tt>Exit</tt> action.
	 * 
	 * @param event Event that trigger this method call.
	 */
	void exit(ActionEvent event);

}
