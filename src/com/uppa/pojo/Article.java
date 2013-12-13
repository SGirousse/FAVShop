package com.uppa.pojo;

import java.util.Date;

public class Article {
	
	private int _id;			//should be auto-incremented from db insert
	private String _barcode;	
	private String _title;		
	private String _description;
	private String _url;
	private String _store;		
	private float _initprice;	
	private float _sailprice;
	private Date _endofsail;
	private Date _flashdate;
	
	public Article(){
	}
	
	public Article(String barcode, String store, float initprice){
		_barcode=barcode;
		_store=store;
		_initprice=initprice;
	}
	
	public Article(String barcode, String title, String description, String url, 
			String store, float initprice, float sailprice, Date endofsail,
			Date flashdate){
		_barcode=barcode;
		_title=title;
		_description=description;
		_url=url;
		_store=store;
		_initprice=initprice;
		_sailprice=sailprice;
		_endofsail=endofsail;
		_flashdate=flashdate;		
	}
	
	public Article(int id, String barcode, String title, String description,
			String url, String store, float initprice, float sailprice, 
			Date endofsail,	Date flashdate){
		_id=id;
		_barcode=barcode;
		_title=title;
		_description=description;
		_url=url;
		_store=store;
		_initprice=initprice;
		_sailprice=sailprice;
		_endofsail=endofsail;
		_flashdate=flashdate;		
	}
	
	public void setId(int id){
		_id=id;
	}
	
	public int getId(){
		return _id;
	}
	
	public void setBarcode(String barcode){
		_barcode=barcode;
	}
	
	public String getBarcode(){
		return _barcode;
	}
	
	public void setTitle(String title){
		_title=title;
	}
	
	public String getTitle(){
		return _title;
	}
	
	public void setDescription(String description){
		_description=description;
	}
	
	public String getDescription(){
		return _description;
	}
	
	public void setUrl(String url){
		_url=url;
	}
	
	public String getUrl(){
		return _url;
	}
	
	public void setStore(String store){
		_store=store;
	}
	
	public String getStore(){
		return _store;
	}
	
	public void setInitprice(float initprice){
		_initprice=initprice;
	}
	
	public float getInitprice(){
		return _initprice;
	}
	
	public void setSailprice(float sailprice){
		_sailprice=sailprice;
	}
	
	public float getSailprice(){
		return _sailprice;
	}
	
	public void setEndofsail(Date endofsail){
		_endofsail=endofsail;
	}
	
	public Date getEndofsail(){
		return _endofsail;
	}
	
	public void setFlashdate(Date flashdate){
		_flashdate=flashdate;
	}
	
	public Date getFlashdate(){
		return _flashdate;
	}
}
