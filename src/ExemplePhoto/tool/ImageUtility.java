package ExemplePhoto.tool;

import java.io.FileNotFoundException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
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
	
	public static void display_photo(Activity activity, ImageView imageView, Uri uri, int widthHope, int heightHope){
		//Declaration des variables
		InputStream imageStream;
        Bitmap photo;
        int orientation;
        
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
				if(widthHope > 0 || heightHope > 0){
					photo = resizedBitmap(photo,widthHope,heightHope);
				}
				
				//On affiche la photo
	        	imageView.setImageBitmap(photo);
	        }   
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Bitmap resizedBitmap(Bitmap image, int widthHope, int heightHope) {
		//Declaration des variables
		int width = image.getWidth();
        int height = image.getHeight();
        float widthRatio = width / widthHope;
        float heightRatio = height / heightHope;
        
        //Si le ratio de la largeur est superieur a celle de la hauteur
        //La transformation sera basee sur la largeur
        if (widthRatio > heightRatio) {
            width = widthHope;
            height = (int) (height / widthRatio);
        } else { //Sinon sur la hauteur
            height = heightHope;
            width = (int) (width / heightRatio);
        }
        
        //Renvoie de l'image transforme
        return Bitmap.createScaledBitmap(image, width, height, true);
	}
}
