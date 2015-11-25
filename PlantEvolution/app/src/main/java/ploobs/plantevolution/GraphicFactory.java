package ploobs.plantevolution;

import android.content.Context;

public class GraphicFactory {
	
	
	private GraphicFactory(){}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int mBytesPerFloat= 4;

	/**
	   * SingletonHolder is loaded on the first execution of Singleton.getInstance() 
	   * or the first access to SingletonHolder.INSTANCE, not before.
	   */
	  private static class SingletonHolder { 
	    private static final GraphicFactory INSTANCE = new GraphicFactory();
	  }

	  public static GraphicFactory getInstance() {
		    return SingletonHolder.INSTANCE;
		  }

	  public Context getGraphicContext() {
		return graphicContext;
	}

	public void setGraphicContext(Context graphicContext) {
		this.graphicContext = graphicContext;
	}

	private Context graphicContext;
	private int width;
	private int height;
	  
	  
}
