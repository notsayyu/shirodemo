package com.example.shirodemo.config.auth;

import com.example.shirodemo.dao.PermissionMapper;
import com.example.shirodemo.dao.RoleMapper;
import com.example.shirodemo.dao.UserMapper;
import com.example.shirodemo.model.Permission;
import com.example.shirodemo.model.Role;
import com.example.shirodemo.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Realm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    //认证、登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("Realm.doGetAuthenticationInfo()");

        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;//获取用户输入的token
        String name = token.getUsername();
        String password = new String(token.getPassword());
        User user = userMapper.selectByName(name);
        //账户不存在
        if(user == null)
            throw new UnknownAccountException();

        String passwordInDB = user.getPassword();
        SimpleAuthenticationInfo info= null;
        if(passwordInDB.equals(password))
        {
            info = new SimpleAuthenticationInfo(user, passwordInDB, this.getClass().getName());
        }
        //密码错误
        else
        {
            throw new IncorrectCredentialsException();
        }

        return info;
    }

  //权限验证
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
         /*
        * 当没有使用缓存的时候，不断刷新页面的话，这个代码会不断执行，
        * 当其实没有必要每次都重新设置权限信息，所以我们需要放到缓存中进行管理；
        * 当放到缓存中时，这样的话，doGetAuthorizationInfo就只会执行一次了，
        * 缓存过期之后会再次执行。
        */
        System.out.println("权限配置-->Realm.doGetAuthorizationInfo()");

        User user=(User) principalCollection.fromRealm(this.getClass().getName()).iterator().next();//获取session中的用户
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();

        Role role = roleMapper.selectByPrimaryKey(user.getRoleId());
        info.addRole(role.getName());
        List<Permission> permissionList = permissionMapper.selectAll();
        if(permissionList != null)
            permissionList.stream().forEach(permission -> {
                info.addStringPermission(permission.getPermission());
            });


//         //添加角色
//
//        List<String> roleNameList = userService.getRoleNameByUsername(username);
//        info.addRoles(roleNameList);
//
//
//         //添加权限
//        List<String> permissionList = userService.getPermissionNameByUsername(username);
//        info.addStringPermissions(permissionList);
//
//        info.addStringPermissions(permissions);//将权限放入shiro中.
        return info;
    }

//    /**
//     * 将权限对象中的权限code取出.
//     * @param permissions
//     * @return
//     */
//  public Set<String> getStringPermissions(Set<SysPermission> permissions){
//     Set<String> stringPermissions = new HashSet<String>();
//     if(permissions != null){
//         for(SysPermission p : permissions) {
//            stringPermissions.add(p.getPermission());
//          }
//     }
//       return stringPermissions;
//  }
}
