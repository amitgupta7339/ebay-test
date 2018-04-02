/*
 * Author : Pulkit Agarwal(MT2017085)
 * Email : pulkitgarg2493@gmail.com
 * 
 */
//GLOBAL CONSTANTS
var sub_category = localStorage.sub_category_name;
var category = localStorage.category_name;
var category_product_list = ""; // previously "r"
var subcategory_list = "";

// GLOBAL VARIABLES
var product_list = ""; // previously "r_tmp" real time product list shown on
						// the page items
var selected_product_list = "";// currently selected subcategory/category
								// product list
var filter_gui = "";

// filter variables;
var brand = [];
var color = [];
var condition = [];
var warranty = [];
var warrantyType = [];
var clothingSize = [];
var material = [];
var applicable = [];
var gender = [];
var year = [];
var os = [];
var storage = [];

var brand_filt = [];
var condition_filt = [];
var color_filt = [];
var starting_price = -1;
var ending_price = -1;
var warranty_filt = [];
var warrantyType_filt = [];
var clothingSize_filt = [];
var material_filt = [];
var applicable_filt = [];
var gender_filt = [];
var year_filt = [];
var os_filt = [];
var storage_filt = [];

// Default Function Calls

// alert("Function Call");
showSubCategoryList();
showList();

function showSubCategoryList() {
	if (!(category == null || category == "")) {
		$
				.ajax({
					type : "get",
					url : "http://localhost:5224/ebaytester/webapi/category/"
							+ category,
					success : function(response) {
						subcategory_list = JSON.parse(JSON.stringify(response));
						document.getElementById("category_name").innerHTML = category;
						var subCategory_list_gui = "";
						for (y in subcategory_list) {
							subCategory_list_gui += '<p style="font-size:12px">'
									+ '<a href="#" onclick = SubCategoryFilter('
									+ subcategory_list[y].sub_category_id
									+ ')>'
									+ subcategory_list[y].sub_category_name
									+ '</a>' + '</p>';
						}
						document.getElementById("subCategory_list").innerHTML = subCategory_list_gui;

					}
				})
	} else {
		// Code when category not given

	}
}

function createProductListGUI(product_list) {
	var ProductListGUI = '<ul class="list-group">';
	var call_string = "";
	for (y in product_list) {
		call_string = "productDescription(" + product_list[y].productId + ")";
		ProductListGUI += '<li class="list-group-item">' + '<div class="row">'
				+ '<div class = "col-sm-4">' + '<a href="#" onclick='
				+ call_string
				+ '>'
				+ '<img src="'
				+ product_list[y].productImageUrl
				+ '" style="max-width:250px; max-height:250px" alt="product">'
				+ '</a>'
				+ '</div>'
				+ '<div class = "col-sm-8">'
				+ '<h4><a class="hover" onclick='
				+ call_string
				+ '>'
				+ product_list[y].productName
				+ '</a></h4>'
				+ '<h5 style="color:grey">'
				+ product_list[y].productDescription
				+ '</h5>'
				+ '<h4><br><strong>Rs.'
				+ product_list[y].productPrice
				+ '</strong>'
				+ '<button type="button" style = "float:right" class="btn btn-primary" onclick="buybutton('
				+ product_list[y].productId
				+ /* ','+product_list[y].productName+','+product_list[y].productPrice+ */')">Buy Now</button></h5>'
				+ '<h5 style="color:grey"><strong>Seller : </strong> '
				+ product_list[y].sellerName
				+ '</h5>'
				+ '<h5 style="color:grey"><strong>Discount : </strong>'
				+ product_list[y].productDiscount
				+ '%'
				+ '<button type="button" style = "float:right" class="btn btn-primary" onclick="addToCartButton('
				+ product_list[y].productId
				+ /* ','+product_list[y].productName+','+product_list[y].productPrice+ */')">Add To Cart</button></h5>'
				+ '<h5 style="color:grey"><strong>Shipping : </strong>'
				+ product_list[y].productShipping
				+ '</h5>'
				+ '</div>'
				+ '</div>' + '</li>';
	}
	;
	ProductListGUI += '</ul>';
	return ProductListGUI;

}

function SubCategoryFilter(subCategory_id) {

	selected_product_list = category_product_list.filter(function(el) {
		return el.subCatId === subCategory_id;
	});
	product_list = selected_product_list;
	tmp = createProductListGUI(product_list);
	document.getElementById("product_list").innerHTML = tmp;
}

function showBrandFilter(selected_product_list) {
	brand = [];
	for (y in selected_product_list) {
		if (!brand.includes(selected_product_list[y].productBrand)
				&& selected_product_list[y].productBrand != ""
				&& selected_product_list[y].productBrand != null)// if
																	// particular
																	// brand is
																	// not there
																	// in list
																	// then add
																	// new one
		{
			brand.push(selected_product_list[y].productBrand);
		}
	}
	;
	var brandFilterGUI = "";
	if (brand.length > 0) {
		brandFilterGUI += '<p style="font-size:18px;"><strong> Brand</strong></p>';
		for (i = 0; i < brand.length; i++) {
			brandFilterGUI += '<div class="checkbox">'
					+ '<label><input type="checkbox" value="' + brand[i]
					+ '" onchange = "brandFilter(this)">' + brand[i]
					+ '</label>' + '</div>';
		}
		brandFilterGUI += "<br>";
	}
	return brandFilterGUI;
}

function showColorFilter(selected_product_list) {
	color = [];
	for (y in selected_product_list) {
		if (!color.includes(selected_product_list[y].productColor)
				&& selected_product_list[y].productColor != ""
				&& selected_product_list[y].productColor != null)// if
																	// particular
																	// color is
																	// not there
																	// in list
																	// then add
																	// new one
		{
			color.push(selected_product_list[y].productColor);
		}
	}
	;
	var colorFilterGUI = "";
	if (color.length > 0) {
		colorFilterGUI += '<p style="font-size:18px;"><strong> Color</strong></p>';
		for (i = 0; i < color.length; i++) {
			colorFilterGUI += '<div class="checkbox">'
					+ '<label><input type="checkbox" value="' + color[i]
					+ '" onchange = "colorFilter(this)">' + color[i]
					+ '</label>' + '</div>';
		}
		colorFilterGUI += "<br>";
	}
	return colorFilterGUI;
}

function showConditionFilter(selected_product_list) {
	condition = [];
	for (y in selected_product_list) {
		if (!condition.includes(selected_product_list[y].productCondition)
				&& selected_product_list[y].productCondition != ""
				&& selected_product_list[y].productCondition != null)// if
																		// particular
																		// condition
																		// is
																		// not
																		// there
																		// in
																		// list
																		// then
																		// add
																		// new
																		// one
		{
			condition.push(selected_product_list[y].productCondition);
		}
	}
	;
	var conditionFilterGUI = "";
	if (condition.length > 0) {
		conditionFilterGUI += '<p style="font-size:18px;"><strong> Condition</strong></p>';
		for (i = 0; i < condition.length; i++) {
			conditionFilterGUI += '<div class="checkbox">'
					+ '<label><input type="checkbox" value="' + condition[i]
					+ '" onchange = "conditionFilter(this)">' + condition[i]
					+ '</label>' + '</div>';
		}
		conditionFilterGUI += "<br>";
	}
	return conditionFilterGUI
}

function showStorageFilter(selected_product_list) {
	storage = [];
	// alert("Inside");
	for (y in selected_product_list) {
		if (!storage.includes(selected_product_list[y].productStorage)
				&& selected_product_list[y].productStorage != ""
				&& selected_product_list[y].productStorage != null)// if
																	// particular
																	// storage
																	// is not
																	// there in
																	// list then
																	// add new
																	// one
		{
			storage.push(selected_product_list[y].productStorage);
		}
	}
	;
	var storageFilterGUI = "";
	if (storage.length > 0) {
		storageFilterGUI += '<p style="font-size:18px;"><strong> Storage</strong></p>';
		for (i = 0; i < storage.length; i++) {
			storageFilterGUI += '<div class="checkbox">'
					+ '<label><input type="checkbox" value="' + storage[i]
					+ '" onchange = "storageFilter(this)">' + storage[i]
					+ '</label>' + '</div>';
		}
		storageFilterGUI += "<br>";
	}
	return storageFilterGUI;
}

function showOSFilter(selected_product_list) {
	os = [];
	// alert("Inside");
	for (y in selected_product_list) {
		if (!os.includes(selected_product_list[y].productOS)
				&& selected_product_list[y].productOS != ""
				&& selected_product_list[y].productOS != null)// if particular
																// os is not
																// there in list
																// then add new
																// one
		{
			os.push(selected_product_list[y].productOS);
		}
	}
	;
	var osFilterGUI = "";
	if (os.length > 0) {
		osFilterGUI += '<p style="font-size:18px;"><strong> OS</strong></p>';
		for (i = 0; i < os.length; i++) {
			osFilterGUI += '<div class="checkbox">'
					+ '<label><input type="checkbox" value="' + os[i]
					+ '" onchange = "osFilter(this)">' + os[i] + '</label>'
					+ '</div>';
		}
		osFilterGUI += "<br>";
	}
	return osFilterGUI;
}

function showYearFilter(selected_product_list) {
	year = [];
	// alert("Inside");
	for (y in selected_product_list) {
		if (!year.includes(selected_product_list[y].productYear)
				&& selected_product_list[y].productYear != ""
				&& selected_product_list[y].productYear != null)// if particular
																// year is not
																// there in list
																// then add new
																// one
		{
			year.push(selected_product_list[y].productYear);
		}
	}
	;
	var yearFilterGUI = "";
	if (year.length > 0) {
		yearFilterGUI += '<p style="font-size:18px;"><strong> Year</strong></p>';
		for (i = 0; i < year.length; i++) {
			yearFilterGUI += '<div class="checkbox">'
					+ '<label><input type="checkbox" value="' + year[i]
					+ '" onchange = "yearFilter(this)">' + year[i] + '</label>'
					+ '</div>';
		}
		yearFilterGUI += "<br>";
	}
	return yearFilterGUI;
}

function showGenderFilter(selected_product_list) {
	gender = [];
	// alert("Inside");
	for (y in selected_product_list) {
		if (!gender.includes(selected_product_list[y].productGender)
				&& selected_product_list[y].productGender != ""
				&& selected_product_list[y].productGender != null)// if
																	// particular
																	// gender is
																	// not there
																	// in list
																	// then add
																	// new one
		{
			gender.push(selected_product_list[y].productGender);
		}
	}
	;
	var genderFilterGUI = "";
	if (gender.length > 0) {
		genderFilterGUI += '<p style="font-size:18px;"><strong> Gender</strong></p>';
		for (i = 0; i < gender.length; i++) {
			genderFilterGUI += '<div class="checkbox">'
					+ '<label><input type="checkbox" value="' + gender[i]
					+ '" onchange = "genderFilter(this)">' + gender[i]
					+ '</label>' + '</div>';
		}
		genderFilterGUI += "<br>";
	}
	return genderFilterGUI;
}

function showApplicableFilter(selected_product_list) {
	applicable = [];
	// alert("Inside");
	for (y in selected_product_list) {
		if (!applicable.includes(selected_product_list[y].productApplicable)
				&& selected_product_list[y].productApplicable != ""
				&& selected_product_list[y].productApplicable != null)// if
																		// particular
																		// applicable
																		// is
																		// not
																		// there
																		// in
																		// list
																		// then
																		// add
																		// new
																		// one
		{
			applicable.push(selected_product_list[y].productApplicable);
		}
	}
	;
	var applicableFilterGUI = "";
	if (applicable.length > 0) {
		applicableFilterGUI += '<p style="font-size:18px;"><strong> Applicable</strong></p>';
		for (i = 0; i < applicable.length; i++) {
			applicableFilterGUI += '<div class="checkbox">'
					+ '<label><input type="checkbox" value="' + applicable[i]
					+ '" onchange = "applicableFilter(this)">' + applicable[i]
					+ '</label>' + '</div>';
		}
		applicableFilterGUI += "<br>";
	}
	return applicableFilterGUI;
}

function showMaterialFilter(selected_product_list) {
	material = [];
	// alert("Inside");
	for (y in selected_product_list) {
		if (!material.includes(selected_product_list[y].productMaterial)
				&& selected_product_list[y].productMaterial != ""
				&& selected_product_list[y].productMaterial != null)// if
																	// particular
																	// material
																	// is not
																	// there in
																	// list then
																	// add new
																	// one
		{
			material.push(selected_product_list[y].productMaterial);
		}
	}
	;
	var materialFilterGUI = "";
	if (material.length > 0) {
		materialFilterGUI += '<p style="font-size:18px;"><strong> Material</strong></p>';
		for (i = 0; i < material.length; i++) {
			materialFilterGUI += '<div class="checkbox">'
					+ '<label><input type="checkbox" value="' + material[i]
					+ '" onchange = "materialFilter(this)">' + material[i]
					+ '</label>' + '</div>';
		}
		materialFilterGUI += "<br>";
	}
	return materialFilterGUI;
}

function showClothingSizeFilter(selected_product_list) {
	clothingSize = [];
	// alert("Inside");
	for (y in selected_product_list) {
		if (!clothingSize
				.includes(selected_product_list[y].productClothingSize)
				&& selected_product_list[y].productClothingSize != ""
				&& selected_product_list[y].productClothingSize != null)// if
																		// particular
																		// clothingSize
																		// is
																		// not
																		// there
																		// in
																		// list
																		// then
																		// add
																		// new
																		// one
		{
			clothingSize.push(selected_product_list[y].productClothingSize);
		}
	}
	;
	var clothingSizeFilterGUI = "";
	if (clothingSize.length > 0) {
		clothingSizeFilterGUI += '<p style="font-size:18px;"><strong> ClothingSize</strong></p>';
		for (i = 0; i < clothingSize.length; i++) {
			clothingSizeFilterGUI += '<div class="checkbox">'
					+ '<label><input type="checkbox" value="' + clothingSize[i]
					+ '" onchange = "clothingSizeFilter(this)">'
					+ clothingSize[i] + '</label>' + '</div>';
		}
		clothingSizeFilterGUI += "<br>";
	}
	return clothingSizeFilterGUI;
}

function showWarrantyTypeFilter(selected_product_list) {
	warrantyType = [];
	// alert("Inside1");
	for (y in selected_product_list) {
		if (!warrantyType
				.includes(selected_product_list[y].productWarrantyType)
				&& selected_product_list[y].productWarrantyType != ""
				&& selected_product_list[y].productWarrantyType != null)// if
																		// particular
																		// warrantyType
																		// is
																		// not
																		// there
																		// in
																		// list
																		// then
																		// add
																		// new
																		// one
		{
			warrantyType.push(selected_product_list[y].productWarrantyType);
		}
	}
	;
	// alert("inside2W");
	var warrantyTypeFilterGUI = "";
	if (warrantyType.length > 0) {
		warrantyTypeFilterGUI += '<p style="font-size:18px;"><strong> WarrantyType</strong></p>';
		for (i = 0; i < warrantyType.length; i++) {
			warrantyTypeFilterGUI += '<div class="checkbox">'
					+ '<label><input type="checkbox" value="' + warrantyType[i]
					+ '" onchange = "warrantyTypeFilter(this)">'
					+ warrantyType[i] + '</label>' + '</div>';
		}
		warrantyTypeFilterGUI += "<br>";
	}
	return warrantyTypeFilterGUI;
}

function showWarrantyFilter(selected_product_list) {
	warranty = [];
	// alert("Inside");
	for (y in selected_product_list) {
		if (!warranty.includes(selected_product_list[y].productWarranty)
				&& selected_product_list[y].productWarranty != ""
				&& selected_product_list[y].productWarranty != null)// if
																	// particular
																	// warranty
																	// is not
																	// there in
																	// list then
																	// add new
																	// one
		{
			warranty.push(selected_product_list[y].productWarranty);
		}
	}
	;
	var warrantyFilterGUI = "";
	if (warranty.length > 0) {
		warrantyFilterGUI += '<p style="font-size:18px;"><strong> Warranty</strong></p>';
		for (i = 0; i < warranty.length; i++) {
			warrantyFilterGUI += '<div class="checkbox">'
					+ '<label><input type="checkbox" value="' + warranty[i]
					+ '" onchange = "warrantyFilter(this)">' + warranty[i]
					+ '</label>' + '</div>';
		}
		warrantyFilterGUI += "<br>";
	}
	return warrantyFilterGUI;
}

function showList() {
	// alert("once");
	var url_proceed;

	if ((category == null || category == "")) {
		url_proceed = "sub_category/" + sub_category;
	} else {
		url_proceed = "category/" + category;
	}

	$
			.ajax({
				type : "get",
				url : "http://localhost:5224/ebaytester/webapi/products/"
						+ url_proceed,
				success : function(response) {

					category_product_list = JSON
							.parse(JSON.stringify(response));
					selected_product_list = category_product_list;
					product_list = selected_product_list;

					filter_gui = "";
					filter_gui += showBrandFilter(selected_product_list);
					filter_gui += showColorFilter(selected_product_list);
					filter_gui += showConditionFilter(selected_product_list);
					filter_gui += showWarrantyFilter(selected_product_list);
					// alert("inside");
					filter_gui += showWarrantyTypeFilter(selected_product_list);
					// alert("inside3");
					filter_gui += showApplicableFilter(selected_product_list);
					// alert("inside4");
					filter_gui += showGenderFilter(selected_product_list);
					// alert("inside2");
					filter_gui += showStorageFilter(selected_product_list);
					// alert("inside 3");
					filter_gui += showOSFilter(selected_product_list);
					// alert("inside 4");
					filter_gui += showYearFilter(selected_product_list);
					// alert("inside 5");
					filter_gui += showMaterialFilter(selected_product_list);

					filter_gui += showClothingSizeFilter(selected_product_list);
					// alert("inside3");

					document.getElementById("product_list").innerHTML = createProductListGUI(product_list);
					document.getElementById("filters").innerHTML = filter_gui;

				}
			});
}
// var brand=[];

function buybutton(prod_id) {
	// alert("inside");
	localStorage.product_id_buynow = prod_id;
	if(localStorage.user_Id==null)
		{
		 window.location = "http://localhost:5224/ebaytester/login.html";
		}
	else
		{
		 window.location = "http://localhost:5224/ebaytester/orderReview.html";
		}
}

function addToCartButton(prod_id) {
	alert(prod_id);
	if(localStorage.user_Id==null)
		{
		window.location="http://localhost:5224/ebaytester/login.html";
		}
	else
		{
	$.ajax({
	       type: "post",
	       url:  "http://localhost:5224/ebaytester/webapi/cart/addToCart/"+prod_id+"/"+localStorage.user_Id+"/"+1,
	       complete: function(response){
             window.location="http://localhost:5224/ebaytester/cart.html";
	       	}
		   })
		}
}

function productDescription(prod_id) {
	// document.write("Hey");
	localStorage.prod_id = prod_id;
	// document.write("Hey");
	// System.out.println("Till here");
	// System.out.println(localStorage.getItem("prod_id"));
	// console.log("Prod_id Fetched");
	// document.write(prod_id);
	window.location = "http://localhost:5224/ebaytester/product_description.html";
}

function clothingSizeFilter(obj) {
	if ($(obj).is(":checked")) {// When checked
		clothingSize_filt.push($(obj).val());
		product_list = getListByFilters(selected_product_list);
		document.getElementById("product_list").innerHTML = createProductListGUI(product_list);
		$("#sortFilter").val("None");
	} else {
		clothingSize_filt.splice(clothingSize_filt.indexOf($(obj).val()), 1);// to
																				// delete
																				// the
																				// unchecked
																				// element
		product_list = getListByFilters(selected_product_list);
		document.getElementById("product_list").innerHTML = createProductListGUI(product_list);
		$("#sortFilter").val("None");
	}

}

function materialFilter(obj) {
	if ($(obj).is(":checked")) {// When checked
		material_filt.push($(obj).val());
		product_list = getListByFilters(selected_product_list);
		document.getElementById("product_list").innerHTML = createProductListGUI(product_list);
		$("#sortFilter").val("None");
	} else {
		material_filt.splice(material_filt.indexOf($(obj).val()), 1);// to
																		// delete
																		// the
																		// unchecked
																		// element
		product_list = getListByFilters(selected_product_list);
		document.getElementById("product_list").innerHTML = createProductListGUI(product_list);
		$("#sortFilter").val("None");
	}

}

function applicableFilter(obj) {
	if ($(obj).is(":checked")) {// When checked
		applicable_filt.push($(obj).val());
		product_list = getListByFilters(selected_product_list);
		document.getElementById("product_list").innerHTML = createProductListGUI(product_list);
		$("#sortFilter").val("None");

	} else {
		applicable_filt.splice(applicable_filt.indexOf($(obj).val()), 1);// to
																			// delete
																			// the
																			// unchecked
																			// element
		product_list = getListByFilters(selected_product_list);
		document.getElementById("product_list").innerHTML = createProductListGUI(product_list);
		$("#sortFilter").val("None");
	}

}

function genderFilter(obj) {
	if ($(obj).is(":checked")) {// When checked
		gender_filt.push($(obj).val());
		product_list = getListByFilters(selected_product_list);
		document.getElementById("product_list").innerHTML = createProductListGUI(product_list);
		$("#sortFilter").val("None");

	} else {
		gender_filt.splice(gender_filt.indexOf($(obj).val()), 1);// to delete
																	// the
																	// unchecked
																	// element
		product_list = getListByFilters(selected_product_list);
		document.getElementById("product_list").innerHTML = createProductListGUI(product_list);
		$("#sortFilter").val("None");
	}

}

function yearFilter(obj) {
	if ($(obj).is(":checked")) {// When checked
		year_filt.push($(obj).val());
		product_list = getListByFilters(selected_product_list);
		document.getElementById("product_list").innerHTML = createProductListGUI(product_list);
		$("#sortFilter").val("None");

	} else {
		year_filt.splice(year_filt.indexOf($(obj).val()), 1);// to delete the
																// unchecked
																// element
		product_list = getListByFilters(selected_product_list);
		document.getElementById("product_list").innerHTML = createProductListGUI(product_list);
		$("#sortFilter").val("None");
	}

}

function osFilter(obj) {
	if ($(obj).is(":checked")) {// When checked
		os_filt.push($(obj).val());
		product_list = getListByFilters(selected_product_list);
		document.getElementById("product_list").innerHTML = createProductListGUI(product_list);
		$("#sortFilter").val("None");
	} else {
		os_filt.splice(os_filt.indexOf($(obj).val()), 1);// to delete the
															// unchecked element
		product_list = getListByFilters(selected_product_list);
		document.getElementById("product_list").innerHTML = createProductListGUI(product_list);
		$("#sortFilter").val("None");
	}

}

function storageFilter(obj) {
	if ($(obj).is(":checked")) {// When checked
		storage_filt.push($(obj).val());
		product_list = getListByFilters(selected_product_list);
		document.getElementById("product_list").innerHTML = createProductListGUI(product_list);
		$("#sortFilter").val("None");
	} else {
		storage_filt.splice(storage_filt.indexOf($(obj).val()), 1);// to delete
																	// the
																	// unchecked
																	// element

		product_list = getListByFilters(selected_product_list);
		document.getElementById("product_list").innerHTML = createProductListGUI(product_list);
		$("#sortFilter").val("None");
	}

}

function warrantyTypeFilter(obj) {
	if ($(obj).is(":checked")) {// When checked
		warrantyType_filt.push($(obj).val());
		product_list = getListByFilters(selected_product_list);
		document.getElementById("product_list").innerHTML = createProductListGUI(product_list);
		$("#sortFilter").val("None");

	} else {
		warrantyType_filt.splice(warrantyType_filt.indexOf($(obj).val()), 1);// to
																				// delete
																				// the
																				// unchecked
																				// element

		product_list = getListByFilters(selected_product_list);
		document.getElementById("product_list").innerHTML = createProductListGUI(product_list);
		$("#sortFilter").val("None");
	}

}

function warrantyFilter(obj) {
	if ($(obj).is(":checked")) {// When checked
		warranty_filt.push($(obj).val());
		product_list = getListByFilters(selected_product_list);
		document.getElementById("product_list").innerHTML = createProductListGUI(product_list);
		$("#sortFilter").val("None");

	} else {
		warranty_filt.splice(warranty_filt.indexOf($(obj).val()), 1);// to
																		// delete
																		// the
																		// unchecked
																		// element
		product_list = getListByFilters(selected_product_list);
		document.getElementById("product_list").innerHTML = createProductListGUI(product_list);
		$("#sortFilter").val("None");
	}

}

function brandFilter(obj) {
	if ($(obj).is(":checked")) {// When checked
		brand_filt.push($(obj).val());
		product_list = getListByFilters(selected_product_list);
		document.getElementById("product_list").innerHTML = createProductListGUI(product_list);
		$("#sortFilter").val("None");

	} else {
		brand_filt.splice(brand_filt.indexOf($(obj).val()), 1);// to delete the
																// unchecked
																// element
		product_list = getListByFilters(selected_product_list);
		document.getElementById("product_list").innerHTML = createProductListGUI(product_list);
		$("#sortFilter").val("None");
	}

}

function conditionFilter(obj) {
	if ($(obj).is(":checked")) {// When checked
		condition_filt.push($(obj).val());
		product_list = getListByFilters(selected_product_list);
		document.getElementById("product_list").innerHTML = createProductListGUI(product_list);
		$("#sortFilter").val("None");

	} else {
		condition_filt.splice(condition_filt.indexOf($(obj).val()), 1);// to
																		// delete
																		// the
																		// unchecked
																		// element
		product_list = getListByFilters(selected_product_list);
		document.getElementById("product_list").innerHTML = createProductListGUI(product_list);
		$("#sortFilter").val("None");
	}

}

function colorFilter(obj) {
	if ($(obj).is(":checked")) {// When checked
		color_filt.push($(obj).val());
		product_list = getListByFilters(selected_product_list);
		document.getElementById("product_list").innerHTML = createProductListGUI(product_list);
		$("#sortFilter").val("None");
	} else {
		color_filt.splice(color_filt.indexOf($(obj).val()), 1);// to delete the
																// unchecked
																// element
		product_list = getListByFilters(selected_product_list);
		document.getElementById("product_list").innerHTML = createProductListGUI(product_list);
		$("#sortFilter").val("None");
	}
}

function getListByFilters(selected_product_list) {
	list = selected_product_list
			.filter(function(el) {
				return ((starting_price != -1 && ending_price != -1) ? (el.productPrice >= starting_price && el.productPrice <= ending_price)
						: true)
						&& (brand_filt.length != 0 ? brand_filt
								.includes(el.productBrand) : true)
						&& (color_filt.length != 0 ? color_filt
								.includes(el.productColor) : true)
						&& (condition_filt.length != 0 ? condition_filt
								.includes(el.productCondition) : true)
						&& (warranty_filt.length != 0 ? warranty_filt
								.includes(el.productWarranty) : true)
						&& (warrantyType_filt.length != 0 ? warrantyType_filt
								.includes(el.productWarrantyType) : true)
						&& (storage_filt.length != 0 ? storage_filt
								.includes(el.productStorage) : true)
						&& (os_filt.length != 0 ? os_filt
								.includes(el.productOS) : true)
						&& (year_filt.length != 0 ? year_filt
								.includes(el.productYear) : true)
						&& (gender_filt.length != 0 ? gender_filt
								.includes(el.productGender) : true)
						&& (applicable_filt.length != 0 ? applicable_filt
								.includes(el.productApplicable) : true)
						&& (material_filt.length != 0 ? material_filt
								.includes(el.productMaterial) : true)
						&& (clothingSize_filt.length != 0 ? clothingSize_filt
								.includes(el.productClothingSize) : true);
			});
	return list;
}

function priceFilter(start_price, end_price) {
	starting_price = start_price;
	ending_price = end_price;
	product_list = selected_product_list.filter(function(el) {
		return (el.productPrice >= start_price && el.productPrice <= end_price)
				&& (brand_filt.length != 0 ? brand_filt
						.includes(el.productBrand) : true)
				&& (color_filt.length != 0 ? color_filt
						.includes(el.productColor) : true)
				&& (condition_filt.length != 0 ? condition_filt
						.includes(el.productCondition) : true);
	});
	document.getElementById("product_list").innerHTML = createProductListGUI(product_list);
	$("#sortFilter").val("None");
}

function SortFilter(val) {
	var i, j;
	alert("inside");
	if (val == "LowToHigh") {
		for (i = product_list.length - 1; i >= 0; i--)// bubble sort
		{
			for (j = 0; j < i; j++) {
				if (product_list[j].productPrice > product_list[j + 1].productPrice) {
					tmp = product_list[j];
					product_list[j] = product_list[j + 1];
					product_list[j + 1] = tmp;
					// document.write(tmp);
				}
			}
		}
		document.getElementById("product_list").innerHTML = createProductListGUI(product_list);

	} else {
		if (val == "HighToLow") {
			for (i = product_list.length - 1; i >= 0; i--)// bubble sort
			{
				for (j = 0; j < i; j++) {
					if (product_list[j].productPrice < product_list[j + 1].productPrice) {
						tmp = product_list[j];
						product_list[j] = product_list[j + 1];
						product_list[j + 1] = tmp;
						// document.write(tmp);
					}
				}
			}

			document.getElementById("product_list").innerHTML = createProductListGUI(product_list);

		}
	}
}

function ChangeView(val) {
	if (val == "list") {
		window.location = "http://localhost:5224/ebaytester/prod_list_1.html"

	} else {

		window.location = "http://localhost:5224/ebaytester/product_list_2.html"
	}

}
