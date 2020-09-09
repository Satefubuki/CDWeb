package com.springAPI.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springAPI.model.FormWrapper;
import com.springAPI.model.Result;
import com.springAPI.dto.BrandDTO;
import com.springAPI.dto.ProductDTO;
import com.springAPI.dto.ProductTypeDTO;
import com.springAPI.dto.UserDTO;
import com.springAPI.message.ResponseMessage;
import com.springAPI.service.FilesStorageService;
import com.springAPI.service.ProductService;
import com.springAPI.service.ProductTypeService;

@RestController
public class ProductController {
	@Autowired
	ProductService productService;

	@Autowired
	FilesStorageService filesStorageService;

//	@GetMapping("/product" )
//	public List<ProductDTO> getProducts() {
//		return productService.getProducts();
//	}

	//lấy 1 sản phẩm theo id
	@GetMapping("/product/{id}")
	public Result<ProductDTO> findOne(@PathVariable("id") long productId) {
		ProductDTO dto = productService.findOne(productId);
		return new Result<ProductDTO>(0, dto);
	}

	//lấy tất cả sản phẩm
	@GetMapping("/product")
	public Page<ProductDTO> findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "3") Integer size) {
		PageRequest request = new PageRequest(page - 1, size);
		return productService.findAll(request);
	}
	
	//tìm kiếm thei từ khóa
	@GetMapping("/product/search")
	public Page<ProductDTO> search(@RequestParam(value = "name") String name, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "12") Integer size){
		System.out.println("------------" + name);
		PageRequest request = new PageRequest(page - 1, size);
		return productService.searchProduct(name, request);
	}
	
	//lấy sản phẩm theo loại
	@GetMapping("/product/type/{id}")
	public Page<ProductDTO> findbyType(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "3") Integer size, @PathVariable("id") long typeId) {
		PageRequest request = new PageRequest(page - 1, size);
		return productService.findAllByType(request, typeId);
	}
	//lấy sản phẩm theo brand
		@GetMapping("/product/brand/{id}")
		public Page<ProductDTO> findbyBrand(@RequestParam(value = "page", defaultValue = "1") Integer page,
				@RequestParam(value = "size", defaultValue = "3") Integer size, @PathVariable("id") long brandId) {
			PageRequest request = new PageRequest(page - 1, size);
			return productService.findAllByBrand(request, brandId);
		}

	//thêm sản phẩm
	@PostMapping("/product")
	public Result<ProductDTO> addProduct(@ModelAttribute FormWrapper wrap) {
		 System.out.println(wrap.getName());
		System.out.println("product id" + wrap.getName());
		System.out.println("product name" + wrap.getName());
		ProductTypeDTO type = new ProductTypeDTO();
		type.setId(Long.parseLong(wrap.getType()));
		BrandDTO brand = new BrandDTO();
		brand.setId(Long.parseLong(wrap.getBrand()));
		ProductDTO p = new ProductDTO();
		p.setId(0);
		p.setName(wrap.getName());
		p.setPrice(Double.parseDouble(wrap.getPrice()));
		p.setColor(wrap.getColor());
		p.setStock(Integer.parseInt(wrap.getStock()));
		p.setSale(Integer.parseInt(wrap.getSale()));
		p.setType(type);
		p.setBrand(brand);


		if (wrap.getFile() != null) {
			p.setMainImg(filesStorageService.save(wrap.getFile()));
		}
		if (wrap.getFile2() != null) {
			String s1 = filesStorageService.save(wrap.getFile2());
			p.setSubImg(s1);
			//System.out.println(s1);
		}
		if (wrap.getFile3() != null) {
			p.setSubImg2(filesStorageService.save(wrap.getFile3()));
		}
		p = productService.save(p);
		

		return new Result<ProductDTO>(0, p);

	}

//	@PostMapping("/product/add")
//	public ProductDTO add(@RequestBody ProductDTO pro) {
//		return productService.save(pro);
//
//	}

//	@PutMapping("/product/{id}")
//	public ProductDTO edit(@PathVariable("id") long productId, @RequestBody ProductDTO product) {
//		 if (productId == product.getId()) {
//	            return productService.update(product);
//	        }
//		return product;
//		
//	}
	// sửa sản phẩm
	@PostMapping("/product/edit/{id}")
	public ProductDTO editProduct(@ModelAttribute FormWrapper wrap, @PathVariable("id") long productId) {
		// System.out.println(wrap.getName());
		ProductTypeDTO type = new ProductTypeDTO();
		type.setId(Long.parseLong(wrap.getType()));
		BrandDTO brand = new BrandDTO();
		brand.setId(Long.parseLong(wrap.getBrand()));
		ProductDTO newProduct = new ProductDTO();
		newProduct.setId(productId);
		newProduct.setName(wrap.getName());
		newProduct.setPrice(Double.parseDouble(wrap.getPrice()));
		newProduct.setColor(wrap.getColor());
		newProduct.setStock(Integer.parseInt(wrap.getStock()));
		newProduct.setSale(Integer.parseInt(wrap.getSale()));
		newProduct.setType(type);
		newProduct.setBrand(brand);
		newProduct.setMainImg(wrap.getMain());
		newProduct.setSubImg(wrap.getSub());
		newProduct.setSubImg2(wrap.getSub1());

		if (wrap.getFile() != null) {
			filesStorageService.deleteOne(newProduct.getMainImg());
			newProduct.setMainImg(filesStorageService.save(wrap.getFile()));
		}
		if (wrap.getFile2() != null) {
			filesStorageService.deleteOne(newProduct.getSubImg());

			newProduct.setSubImg(filesStorageService.save(wrap.getFile2()));
		}
		if (wrap.getFile3() != null) {
			filesStorageService.deleteOne(newProduct.getSubImg2());

			newProduct.setSubImg2(filesStorageService.save(wrap.getFile3()));
		}
		newProduct = productService.save(newProduct);


		return newProduct;

	}

	//Xóa sản phẩm
	@DeleteMapping("/product/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long productId) {
		productService.delete(productId);
		return ResponseEntity.ok().build();
	}
}
