package com.project.occ;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.imagezoom.ImageAttacher;
import com.imagezoom.ImageAttacher.OnMatrixChangedListener;
import com.imagezoom.ImageAttacher.OnPhotoTapListener;

/**
 * 
 * @author JiMin
 *
 * Displays the map of Orange Coast College.
 */
public class Map extends Activity {
	
	/**
	 * called when the activity is first created
	 * */
    ImageView mImaView;

    /**
	 * Called when a new hardware key event occurs.
	 * 
	 * @param keyCode- A key code that presents the button pressed, 
	 * 		from KeyEvent
	 * @param event- The KeyEvent object that defines the button action.
	 * 
	 * @return If you handled the event, return true. 
	 * 		If you want to allow the event to be handled by 
	 * 		the next receiver, return false.
	 */
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
			Intent ourIntent = new Intent("com.project.occ.TESTMENU");
			startActivity(ourIntent);
			
			return true;
		}
		
		return super.onKeyDown(keyCode, event);
	}
	
    /**
	 * Called when the activity is starting.
	 * This is where most initialization should go.
	 * 
	 * @param savedInstanceState- If the activity is being re-initialized
	 * 		 	after previously being shut down then this Bundle contains 
	 * 			the data it most recently supplied in onSaveInstanceState(Bundle).
	 * 			Note: Otherwise it is null.
	 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        mImaView = (ImageView) findViewById(R.id.mapView);

        //Brings .png file (campus_map_sml)
        Bitmap bimtBitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.campus_map_sml);
        mImaView.setImageBitmap(bimtBitmap);

        /**
         * Use Simple ImageView
         */
        usingSimpleImage(mImaView);
    }

    /**
     * Allow users to control the size of image (map).
     * 
     * @param imageView
     */
    public void usingSimpleImage(ImageView imageView) {
        ImageAttacher mAttacher = new ImageAttacher(imageView);
        ImageAttacher.MAX_ZOOM = 2.0f; // Double the current Size
        ImageAttacher.MIN_ZOOM = 0.5f; // Half the current Size
        MatrixChangeListener mMaListener = new MatrixChangeListener();
        mAttacher.setOnMatrixChangeListener(mMaListener);
        PhotoTapListener mPhotoTap = new PhotoTapListener();
        mAttacher.setOnPhotoTapListener(mPhotoTap);
    }

    /**
     * Detects common gestures (double tap), and adjusts size of the map.
     */
    private class PhotoTapListener implements OnPhotoTapListener {

        @Override
        public void onPhotoTap(View view, float x, float y) {
        }
    }

    /**
     * The transform matrix of this view, which is calculated based on 
     * the current scale, and pivot properties.
     */
    private class MatrixChangeListener implements OnMatrixChangedListener {

        @Override
        public void onMatrixChanged(RectF rect) {

        }
    }
}