package product;

import objects.ProductObject;

import java.sql.ResultSet;
import java.util.*;

import connection.ShareControl;

public interface ProductService extends ShareControl {
	boolean addProduct(ProductObject p);

	boolean editProduct(ProductObject p, PRODUCT_EDIT_TYPE type);

	boolean delProduct(ProductObject p);

	ResultSet getProduct(int id);

	ArrayList<ResultSet> getProducts(ProductObject similar, int at, byte total, PRODUCT_SORT_TYPE type);
}
