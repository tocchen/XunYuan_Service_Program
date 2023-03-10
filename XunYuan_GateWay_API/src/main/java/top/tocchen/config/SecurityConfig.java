//package top.tocchen.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.stereotype.Component;
//import top.tocchen.data.UserRole;
//import top.tocchen.enums.UserRoleEnum;
//import top.tocchen.filter.JwtAuthenticationTokenFilter;
//import top.tocchen.security.CustomUserDetails;
//import top.tocchen.security.RestAuthenticationEntryPoint;
//import top.tocchen.security.RestfulAccessDeniedHandler;
//import top.tocchen.vo.UserAuthVO;
//
//import javax.servlet.Filter;
//
///**
// * @author tocchen
// * @date 2023/2/22 18:40
// * @since jdk 1.8
// * SpringSecurity 配置
// **/
//@Component
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled=true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
////    @Autowired DB Service
////    private UmsAdminService adminService;
//    @Autowired
//    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
//    @Autowired
//    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf()// 由于使用的是JWT，我们这里不需要csrf
//                .disable()
//                .sessionManagement()// 基于token，所以不需要session
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, // 允许对于网站静态资源的无授权访问
//                        "/",
//                        "/*.html",
//                        "/favicon.ico",
//                        "/**/*.html",
//                        "/**/*.css",
//                        "/**/*.js",
//                        "/swagger-resources/**",
//                        "/v2/api-docs/**"
//                )
//                .permitAll()
//                .antMatchers("/admin/login", "/admin/register")// 对登录注册要允许匿名访问
//                .permitAll()
//                .antMatchers(HttpMethod.OPTIONS)//跨域请求会先进行一次options请求
//                .permitAll()
////                .antMatchers("/**")//测试时全部运行访问
////                .permitAll()
//                .anyRequest()// 除上面外的所有请求全部需要鉴权认证
//                .authenticated();
//        // 禁用缓存
//        httpSecurity.headers().cacheControl();
//        // 添加JWT filter
//        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//        //添加自定义未授权和未登录结果返回
//        httpSecurity.exceptionHandling()
//                .accessDeniedHandler(restfulAccessDeniedHandler)
//                .authenticationEntryPoint(restAuthenticationEntryPoint);
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService())
//                .passwordEncoder(passwordEncoder());
//    }
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
////        获取登录用户信息
//        return username -> {
////            UserAuthVO userAuthVO = adminService.getAdminByUsername(username);
//            UserAuthVO userAuthVO = new UserAuthVO("1234567","tocchen","123","password", UserRoleEnum.ADMIN_USER);
//            if (userAuthVO != null) {
//                return new CustomUserDetails(userAuthVO);
//            }
//            throw new UsernameNotFoundException("用户名或密码错误");
//        };
//
//    }
//
//    @Bean
//    public Filter jwtAuthenticationTokenFilter(){
//        return new JwtAuthenticationTokenFilter();
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//
//}
