package cn.jbone.cms.api.impl;


import cn.jbone.cms.api.CategoryApi;
import cn.jbone.cms.common.dataobject.CategoryDO;
import cn.jbone.cms.core.service.CategoryService;
import cn.jbone.common.exception.ObjectNotFoundException;
import cn.jbone.common.rpc.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryApiImpl implements CategoryApi {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CategoryService categoryService;

    public Result<Void> addOrUpdate(@RequestBody CategoryDO categoryDO){
        try {
            categoryService.addOrUpdate(categoryDO);
        } catch (Exception e) {
            logger.warn("category addOrUpdate error.",e);
            return Result.wrap500Error(e.getMessage());
        }
        return Result.wrapSuccess();
    }

    public Result<Void> delete(Long id){
        try {
            categoryService.delete(id);
        } catch (ObjectNotFoundException e){
            return Result.wrap404Error(e.getMessage());
        } catch (Exception e) {
            logger.warn("category delete error.",e);
            return Result.wrap500Error(e.getMessage());
        }
        return Result.wrapSuccess();
    }

    @Override
    public Result<CategoryDO> get(Long id) {
        CategoryDO categoryDO = null;
        try {
            categoryDO = categoryService.get(id);
        } catch (Exception e) {
            logger.warn("category get error.",e);
            return Result.wrap500Error(e.getMessage());
        }
        return Result.wrapSuccess(categoryDO);
    }

    public Result<List<CategoryDO>> getCategoryTree(){
        List<CategoryDO> categoryDOS = null;
        try {
            categoryDOS = categoryService.getCategoryTree();
        }catch (Exception e) {
            logger.warn("category getCategoryTree error.",e);
            return Result.wrap500Error(e.getMessage());
        }
        return Result.wrapSuccess(categoryDOS);
    }
}
