package review.classdesign.sample;

import java.awt.event.ActionListener;
import java.util.function.Supplier;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * This class aims to build a {@link JMenu}.
 * 
 * @author fv
 */
public final class MenuBuilder implements Supplier<JMenu> {

	/** Menu that this builder will fill. **/
	private final JMenu target;

	/**
	 * Default constructor.
	 * 
	 * @param label Label to use for our target menu.
	 */
	protected MenuBuilder(final String label) {
		this.target = new JMenu(label);
	}

	/** {@inheritDoc} **/
	@Override
	public JMenu get() {
		return target;
	}
	
	/**
	 * Static factory method that create a {@link JMenuItem} instance
	 * from the given <tt>label</tt> and <tt>listener</tt>.
	 * 
	 * @param label Label for the created item.
	 * @param listener Action listener bind to the created item.
	 * @return Created {@link JMenuItem} instance.
	 */
	public JMenuItem createItem(final String label, final ActionListener listener) {
		final JMenuItem item = target.add(new JMenuItem(label));
		item.addActionListener(listener);
		return item;
	}

	/**
	 * Creates an disabled menu item, and returns a {@link Runnable} object
	 * which can activate it.
	 * 
	 * @param label Label for the created item.
	 * @param listener Action listener bind to the created item.
	 * @return Item activator.
	 */
	public Runnable createActivable(final String label, final ActionListener listener) {
		final JMenuItem item = createItem(label, listener);
		item.setEnabled(false);
		return () -> item.setEnabled(true);
	}

}
