document.getElementById('export').addEventListener('click', ()=>{
	document.getElementById('export-button').click();
});

document.getElementById('import').addEventListener('click', ()=>{
	document.getElementById('import-button').click();
});

/* -------------- upload product images -------------- */
  let fileImg = document.querySelectorAll('.fileUpload');
 // let uploadSection = document.querySelector('.img-upload-section');
  var defaultImgSrc = '/home/images/default-product-img.jpg'
  let buttonAdd = document.querySelector('.button-add-img', true);
  if(buttonAdd != null && buttonAdd.length > 0) {
    buttonAdd.forEach(e => e.addEventListener('click', () => fileImg.click()));
  }
  let imagesData = [];
  
  if(fileImg != null) {
	  fileImg.forEach((e) => e.addEventListener('change', handleInputFileChange));
  }
  var imgTag = document.querySelectorAll('.product-img');
  imgTag.forEach((e) => {
	  if(e.src.includes(defaultImgSrc)) {
		  var parent = e.parentNode;
		  var fileUpload = parent.querySelector('input[type="file"]');
		  e.addEventListener('click', () =>{
		    fileUpload.click();
		  })
	  }
  });
  
  function handleDefaultImgClick(e) {
	  if(e.src.includes(defaultImgSrc)) {
	  	var parent = e.target.parentNode;
	  	var inputUpload = parent.querySelector("input")
	  	inputUpload.click();
	  }
  }

  /*async function handleInputFileChange(e) {
	var uploadSec = e.target.closest('.img-upload-section');
    var imgTag = uploadSec.querySelectorAll('.product-img');
    // console.log(imgTag);
    if(imgTag != null && imgTag.length > 0) {
      imgTag.forEach(item => {
        var src = new String(item.src);
        if(src.includes(defaultImgSrc)) {
          uploadSec.removeChild(item.parentNode)
        }
          
      })
    }
    var selectedFiles = fileImg.files;
    for(var i = 0; i < selectedFiles.length; i++) {
      // Kiểm tra xem tệp có phải là hình ảnh không
      if (file.type.match('image.*')) {
        try {
          var data = await getDataFromBlob(file);
          if(data != null && data != '') {
            var div = document.createElement("div");
            div.className = 'col-3 px-1';
            var imgElement = document.createElement('img');
            imgElement.className = 'product-img glightbox';
            imgElement.src = data;
            imgElement.setAttribute('data-gallery', 'productImgsUpGallery');
            div.appendChild(imgElement);
            var btn = document.createElement('button');
            btn.className = 'btn btn-danger rounded-circle btn-remove-img';
            btn.innerHTML = '-';
            div.appendChild(btn);
            uploadSec.appendChild(div);
          }
        } catch (error) {
          console.log(error);
        }
      }
    }
    handlClickRemoveBtn();
    addDefaultImg(uploadSec);
    var lightBoxGal = Array.from(uploadSec.querySelectorAll('.glightbox'));
    const glightbox = GLightbox({
    selector: ".glightbox",
  	});
  }*/
  
  /**
   * Hàm xử lý input là chị em của thẻ img và không có thuộc tính multiple
   */
  async function handleInputFileChange(e) {
	var parent = e.target.parentNode;
    var selectedFile = e.target.files[0];
    var uploadSec = e.target.closest('.img-upload-section');
    if (selectedFile.type.match('image.*')) {
		try {
          var data = await getDataFromBlob(selectedFile);
          if(data != null && data != '') {
			   var imgElement = parent.querySelector('img');
			   imgElement.className = 'product-img glightbox img-fluid';
	            imgElement.src = data;
	            imgElement.setAttribute('data-gallery', 'productImgsUpGallery');
	            
	            var btn = document.createElement('button');
	            btn.className = 'btn btn-danger rounded-circle btn-remove-img';
	            btn.innerHTML = '-';
	            btn.addEventListener('click', handlClickRemoveBtn);
	            parent.appendChild(btn);
	           
			    /*var lightBoxGal = Array.from(uploadSec.querySelectorAll('.glightbox'));
			    const glightbox = GLightbox({
			    	selector: ".glightbox",
			  	});*/
		  }
		}catch (error) {
          console.log(error);
        }
	}
  }

  const getDataFromBlob = (myBlob) => {
    return new Promise((resolve, reject) => {
        var reader = new FileReader();
        reader.onload = () => {
            resolve(reader.result);
        };
        reader.onerror = reject;
        reader.readAsDataURL(myBlob);
    })
  }

  function addDefaultImg(section) {
    var imgTag = section.querySelectorAll('.product-img')
    if(imgTag.length < 4) {
      for(var i = 4; i > imgTag.length; i--) {
        var div = document.createElement("div");
        div.className = 'col-3 px-1';
        var imgElement = document.createElement('img');
        imgElement.className = 'product-img';
        imgElement.src = defaultImgSrc;
        var input = document.createElement("input");
        input.className = 'fileUpload';
        input.accept = "image/*";
        input.hidden = "true";
        imgElement.addEventListener('click', () =>{
          handleDefaultImgClick();
        })
        div.appendChild(imgElement);
        div.appendChild(input);
        section.appendChild(div);
      }
    }
  }
  function handlClickRemoveBtn(e) {
    var uploadSct = e.target.closest('.img-upload-section');
    var parent = e.target.parentElement;
    uploadSct.removeChild(parent);
    addDefaultImg(uploadSct);
  }
