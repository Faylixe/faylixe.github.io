package review.classdesign.sample;

import java.util.function.Supplier;

import javax.swing.JMenuBar;

/**
 * This class aims to build a {@link JMenuBar}.
 * 
 * @author fv
 */
public final class MenuBarBuilder implements Supplier<JMenuBar> {

	/** Menu bar that this builder will fill. **/
	private final JMenuBar target;

	/** Application controller. **/
	private final Controller controller;

	/**
	 * Default constructor.
	 * 
	 * @param controller Application controller.
	 */
	private MenuBarBuilder(final Controller controller)
	{
		this.target = new JMenuBar();
		this.controller = controller;
	}

	/** {@inheritDoc} **/
	@Override
	public JMenuBar get() {
		return target;
	}

	/**
	 * Creates and fills <tt>File</tt> menu.
	 */
	private void createFileMenu() {
		final MenuBuilder builder = new MenuBuilder("File");
		builder.createItem("Open", controller::open);
		controller.accept(builder.createActivable("Save", controller::save));
		builder.createItem("Exit", controller::exit);
		target.add(builder.get());
	}

	/**
	 * Static factory method, which is our only components
	 * entry point.
	 * 
	 * @param controller Application controller.
	 * @return Created {@link JMenuBar} instance.
	 */
	public static JMenuBar build(final Controller controller) {
		final MenuBarBuilder builder = new MenuBarBuilder(controller);
		builder.createFileMenu();
		return builder.get();
	}

}
