package com.zfd.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zfd.spzx.manager.mapper.ProductDetailsMapper;
import com.zfd.spzx.manager.mapper.ProductMapper;
import com.zfd.spzx.manager.mapper.ProductSkuMapper;
import com.zfd.spzx.manager.service.ProductService;
import com.zfd.spzx.model.dto.product.ProductDto;
import com.zfd.spzx.model.entity.product.Product;
import com.zfd.spzx.model.entity.product.ProductDetails;
import com.zfd.spzx.model.entity.product.ProductSku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Autowired
    private ProductDetailsMapper productDetailsMapper;

    @Override
    public PageInfo<Product> findByPage(Integer page, Integer limit, ProductDto productDto) {

        PageHelper.startPage(page, limit);
        List<Product> productList = productMapper.findByPage(productDto);
        PageInfo<Product> pageInfo = new PageInfo<>(productList);
        return pageInfo;
    }

    @Override
    public void save(Product product) {

        product.setStatus(0);
        product.setAuditStatus(0);
        productMapper.save(product);

        List<ProductSku> productSkuList = product.getProductSkuList();
        for (int i=0;i<productSkuList.size();i++){
            ProductSku productSku = productSkuList.get(i);

            productSku.setSkuCode(product.getId()+"_"+i);
            productSku.setProductId(product.getId());
            productSku.setSkuName(product.getName()+productSku.getSkuSpec());
            productSku.setStatus(0);
            productSku.setSaleNum(0);

            productSkuMapper.save(productSku);
        }

        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductId(product.getId());
        productDetails.setImageUrls(product.getDetailsImageUrls());
        productDetailsMapper.save(productDetails);

    }

    @Override
    public Product getById(Long id) {

        Product product = productMapper.findProductById(id);

        List<ProductSku> productSkuList = productSkuMapper.findProductSkuByProductId(id);
        product.setProductSkuList(productSkuList);

        ProductDetails productDetails =productDetailsMapper.findProductDetailsById(id);
        String imageUrls = productDetails.getImageUrls();
        product.setDetailsImageUrls(imageUrls);

        return product;
    }

    @Override
    public void update(Product product) {
        productMapper.updateById(product);

        List<ProductSku> productSkuList = product.getProductSkuList();
        productSkuList.forEach(productSku -> {
            productSkuMapper.updateById(productSku);
        });

        ProductDetails productDetails = productDetailsMapper.findProductDetailsById(product.getId());
        productDetails.setImageUrls(product.getDetailsImageUrls());
        productDetailsMapper.updateById(productDetails);

    }

    @Override
    public void deleteById(Long id) {
        productMapper.deleteById(id);
        productSkuMapper.deleteByProductId(id);
        productDetailsMapper.deleteByProductId(id);
    }

    @Override
    public void updateAuditStatus(Long id, Integer auditStatus) {
        Product product = new Product();
        product.setId(id);
        if(auditStatus == 1) {
            product.setAuditStatus(1);
            product.setAuditMessage("审批通过");
        } else {
            product.setAuditStatus(-1);
            product.setAuditMessage("审批不通过");
        }
        productMapper.updateById(product);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        Product product = new Product();
        product.setId(id);
        product.setStatus(status);
        productMapper.updateById(product);
    }
}
