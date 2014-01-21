package com.sgf.tool;

import java.io.FileNotFoundException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.widget.ImageView;

public final class ImageUtility {
	
	public static Bitmap rotate(Bitmap img, int angle){
		//Declaration des variables
		Matrix matrix = new Matrix();
		
		//Explicitation de l'angle de rotation a la matrice
		matrix.postRotate(angle);
		
		//Renvoie du bitmap avec une rotation
		return Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
	}
	
	public static int getOrientation(Context context, Uri uri) {

		//Declaration des variables
	    Cursor cursor;
	    
	    //Requete pour connaitre l'orientation de l'image se trouvant a l'URI
	    cursor = context.getContentResolver().query(uri,
	            new String[] { MediaStore.Images.ImageColumns.ORIENTATION }, null, null, null);

	    //Si l'image n'existe pas
	    if (cursor.getCount() != 1) {
	        return -1;
	    }

	    //Le curseur se deplace sur le resultat de la requette
	    cursor.moveToFirst();
	    
	    //Renvoie de l'angle de l'image
	    return cursor.getInt(0);
	}
	
	public static float convertDpToPixel(float dp, Context context){
	    Resources resources = context.getResources();
	    DisplayMetrics metrics = resources.getDisplayMetrics();
	    float px = dp * (metrics.densityDpi / 160f);
	    return px;
	}
	
	public static void display_photo(Activity activity, ImageView imageView, Uri uri, float dpWidthHope, float dpHeightHope){
		//Declaration des variables
		InputStream imageStream;
        Bitmap photo;
        int orientation;
        float pxWidthHope = convertDpToPixel(dpWidthHope,activity);
        float pxHeightHope = convertDpToPixel(dpHeightHope,activity);;
        
        //Affichage de l'image si possible
		try {
			//Recuperation de l'image se trouvant a l'URI
			imageStream = activity.getContentResolver().openInputStream(uri);
			photo = BitmapFactory.decodeStream(imageStream);
			
			//S'il y avait bien une photo a l'URI
			if (photo != null) {
				//Recuperation de l'orientation de l'image
				orientation = getOrientation(activity,uri);
				
				//Test si l'orientation est la meme que lors de la prise de la photo
				if(orientation != 0){
					//On tourne l'image pour l'affichage
					photo = rotate(photo,orientation);
				}
				
				//Modification de l'image si necessaire
				if(pxWidthHope > 0 || pxHeightHope > 0){
					photo = resizedBitmap(photo,pxWidthHope,pxHeightHope);
				}
				
				//On affiche la photo
	        	imageView.setImageBitmap(photo);
	        }   
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Bitmap resizedBitmap(Bitmap image, float pxWidthHope, float pxHeightHope) {
		//Declaration des variables
		float width = image.getWidth();
		float height = image.getHeight();
        float widthRatio = width / pxWidthHope;
        float heightRatio = height / pxHeightHope;
        
        //Si le ratio de la largeur est superieur a celle de la hauteur
        //La transformation sera basee sur la largeur
        if (widthRatio > heightRatio) {
            width = pxWidthHope;
            height = (int) (height / widthRatio);
        } else { //Sinon sur la hauteur
            height = pxHeightHope;
            width = (int) (width / heightRatio);
        }
        
        //Renvoie de l'image transforme
        return Bitmap.createScaledBitmap(image, (int)width, (int)height, true);
	}
}
