package ploobs.plantevolution;

import android.content.Context;
import android.content.res.Resources;

public class GraphicFactory {
	
	
	private GraphicFactory(){}

	public float getRatio(){ return (float)width/(float)height;}

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

	public Resources getResources() {
		return resources;
	}

	public void setResources(Resources resources) {
		this.resources = resources;
	}

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

	private Resources resources;
	private int width;
	private int height;
	  
	  
}
