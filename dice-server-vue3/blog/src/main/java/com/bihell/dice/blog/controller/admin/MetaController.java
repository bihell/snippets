package com.bihell.dice.blog.controller.admin;

import com.bihell.dice.blog.mapper.blogs.MetaMapper;
import com.bihell.dice.blog.model.blog.Meta;
import com.bihell.dice.blog.service.blog.MetaService;
import com.bihell.dice.blog.vo.MetaQueryVO;
import com.bihell.dice.framework.common.api.ApiResult;
import com.bihell.dice.framework.common.api.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 属性(标签和分类)管理 Controller
 *
 * @author bihell
 * @since 2017/8/28 23:16
 */
@RestController
@RequestMapping("/v1/api/admin/meta")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MetaController {

    private final MetaService metaService;
    private final MetaMapper metaMapper;

    /**
     * 获取所有属性
     *
     * @return {@see List<MetaDto>}
     */
    @GetMapping
    public RestResponse getAll(@RequestParam String type,
                               @RequestParam(required = false) String title,
                               @RequestParam(required = false) String snippetFileContent) {
        return RestResponse.ok(metaService.getMetaDtos(type, title, snippetFileContent));
    }

    /**
     * 根据类型获取meta 列表
     * @return {@see List<MetaQueryVO>}
     */
    @GetMapping("/meta_list")
    public ApiResult<List<MetaQueryVO>> getSelectList(@RequestParam String type) {
        return ApiResult.ok(metaMapper.selectByMetaType(type));
    }

    /**
     * 根据name删除分类
     *
     * @param name 属性名
     * @param type 属性类型 {@see Types#CATEGORY},{@see Types#TAG}
     * @return {@see RestResponse.ok()}
     */
    @DeleteMapping
    public RestResponse deleteMeta(@RequestParam String name, @RequestParam String type) {
        if (metaService.deleteMeta(name, type)) {
            return RestResponse.ok();
        }
        return RestResponse.fail();
    }

    /**
     * 添加一个分类
     * @param meta
     * @return
     */
    @PostMapping
    public ApiResult<Boolean> saveMeta(@Valid @RequestBody Meta meta) {
        boolean flag = metaService.saveMeta(meta.getName(), meta.getType());
            return ApiResult.result(flag);
    }

    /**
     * 根据id修改分类
     * @param meta
     * @return
     */
    @PostMapping("{id}")
    public ApiResult<Boolean> updateMeta(@Valid @RequestBody Meta meta) {
        boolean flag =  metaService.updateMeta(meta.getId(), meta.getName(), meta.getType());
        return ApiResult.result(flag);
    }
}
