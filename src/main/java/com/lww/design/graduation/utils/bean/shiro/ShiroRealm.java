package com.lww.design.graduation.utils.bean.shiro;


import com.google.common.base.Strings;
import com.lww.design.graduation.entity.vo.shiro.ShiroPermissionVO;
import com.lww.design.graduation.entity.vo.shiro.ShiroRoleVO;
import com.lww.design.graduation.entity.vo.shiro.ShiroUserVO;
import com.lww.design.graduation.service.permission.UserService;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.impl.util.StringUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



/**
 * Realm是专用于安全框架的Repository
 */
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 授权访问
     * {@link SimpleAuthorizationInfo SimpleAuthorizationInfo}, as it is suitable in most cases.
     *
     * @param principals the primary identifying principals of the AuthorizationInfo that should be retrieved.
     * @return the AuthorizationInfo associated with this principals.
     * @see SimpleAuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取当前登录的用户名
        String userName = (String) super.getAvailablePrincipal(principals);
        log.info("user:{}, login", userName);
        //保存角色
        List<String> roles = new ArrayList<>();
        //保存权限
        List<String> permissions = new ArrayList<>();
        //拿到当前登陆的用户
        ShiroUserVO user = userService.getUserPermissionById(userName);
        if (user != null) {
            List<ShiroRoleVO> userRoleList = user.getRoleVOList();
            if (!CollectionUtils.isEmpty(userRoleList)) {
                for (ShiroRoleVO role : userRoleList) {
                    roles.add(role.getName());
                    List<ShiroPermissionVO> rolePermissionList = role.getPermissionVOList();
                    if (CollectionUtils.isEmpty(rolePermissionList)) {
                        permissions.addAll(
                                rolePermissionList.stream()
                                        .map(ShiroPermissionVO::getSn)
                                        .collect(Collectors.toList()
                                        )
                        );
                    }
                }
            }
        } else {
            throw new AuthorizationException();
        }
        //给当前用户设置角色
        info.addRoles(roles);
        //给当前用户设置权限
        info.addStringPermissions(permissions);
        return info;
    }

    /**
     * 登录认证
     * <p/>
     * For most dataSources, this means just 'pulling' authentication data for an associated subject/user and nothing
     * more and letting Shiro do the rest.  But in some systems, this method could actually perform EIS specific
     * log-in logic in addition to just retrieving data - it is up to the Realm implementation.
     * <p/>
     * A {@code null} return value means that no account could be associated with the specified token.
     *
     * @param authToken the authentication token containing the user's principal and credentials.
     * @return an {@link AuthenticationInfo} object containing account data resulting from the
     * authentication ONLY if the lookup is successful (i.e. account exists and is valid, etc.)
     * @throws AuthenticationException if there is an error acquiring data or performing
     *                                 realm-specific authentication logic for the specified <tt>token</tt>
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
//        UsernamePasswordToken token = (UsernamePasswordToken) authToken;
//        DomyAdmin user=null;
//        try {
//            user=getSystemService().loginAndReturnUser(token.getUsername(), String.valueOf(token.getPassword()));
//        } catch (RuntimeException e) {
//            throw new AuthenticationException("msg:"+e.getMessage());
//        }
//        return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());


        UsernamePasswordToken token = (UsernamePasswordToken) authToken;
//        ShiroUserVO user = userService.getById(token.getUsername());

        // TODO 需要支持邮箱 / 用户名 / 帐号登录
        ShiroUserVO user = userService.getByAccount(Long.valueOf(token.getUsername()));
        log.info("user:{}",user.toString());
        if (user != null) {
            return new SimpleAuthenticationInfo(
                    user.getUserName(), user.getPassWord(), user.toString()
            );
        } else {
            return null;
        }
    }
}
