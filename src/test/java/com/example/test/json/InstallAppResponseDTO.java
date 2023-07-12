package com.example.test.json;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author gaofan
 * @version 1.0
 * @date 2022/11/23 15:08
 **/
@Data
@Slf4j
public class InstallAppResponseDTO {

    private String appId;
    private Integer errorCode;

    public static void main(String[] args) {
        //InitRequestDTO build = InitRequestDTO.builder()
        //        .projectId("92092c60-2820-4934-9c1b-b90019e57b05").accountId("a05cbda6-1096-49fd-8eb8-b23f3ffe11ad")
        //        .id("632a7b9d8c5a9e6a27c7d9cc").build();
        InstallAppResponseDTO installAppResponseDTO = JSONUtil.toBean("{\"value\":{\"state\":1,\"data\":null," +
                "\"exception\":\"Exception: " +
                "Response status code does not indicate success: 400 (Bad Request).\\nStackTrace:    at System.Net" +
                ".Http.HttpResponseMessage.EnsureSuccessStatusCode()\\n   at System.Net.Http.HttpClient" +
                ".GetByteArrayAsyncCore(Task`1 getTask)\\n   at APPS.AppService.Core.Services.AppLibraryService" +
                ".GetFileInfo(String fileUrl) in E:\\\\work\\\\share-file\\\\be-dotnet\\\\app\\\\APPS.AppService" +
                ".Core\\\\APPS.AppService.Core.Services\\\\AppLibraryService.cs:line 367\\n   at MDAppFilePack" +
                ".Controllers.LibraryController.InstallApp(InitRequest request) in " +
                "E:\\\\work\\\\share-file\\\\be-dotnet\\\\appfile\\\\APPSAppFilePack.Controllers\\\\LibraryController" +
                ".cs:line 60\\n   at lambda_method(Closure , Object )\\n   at Microsoft.Extensions.Internal" +
                ".ObjectMethodExecutorAwaitable.Awaiter.GetResult()\\n   at Microsoft.AspNetCore.Mvc.Infrastructure" +
                ".ActionMethodExecutor.AwaitableObjectResultExecutor.Execute(IActionResultTypeMapper mapper, " +
                "ObjectMethodExecutor executor, Object controller, Object[] arguments)\\n   at Microsoft.AspNetCore" +
                ".Mvc.Infrastructure.ControllerActionInvoker.<InvokeActionMethodAsync>g__Awaited|12_0" +
                "(ControllerActionInvoker invoker, ValueTask`1 actionResultValueTask)\\n   at Microsoft.AspNetCore" +
                ".Mvc.Infrastructure.ControllerActionInvoker.<InvokeNextActionFilterAsync>g__Awaited|10_0" +
                "(ControllerActionInvoker invoker, Task lastTask, State next, Scope scope, Object state, Boolean " +
                "isCompleted)\\n   at Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker.Rethrow" +
                "(ActionExecutedContextSealed context)\\n   at Microsoft.AspNetCore.Mvc.Infrastructure" +
                ".ControllerActionInvoker.Next(State& next, Scope& scope, Object& state, Boolean& isCompleted)\\n   " +
                "at Microsoft.AspNetCore.Mvc.Infrastructure.ControllerActionInvoker" +
                ".<InvokeInnerFilterAsync>g__Awaited|13_0(ControllerActionInvoker invoker, Task lastTask, State next," +
                " Scope scope, Object state, Boolean isCompleted)\\n   at Microsoft.AspNetCore.Mvc.Infrastructure" +
                ".ResourceInvoker.<InvokeNextExceptionFilterAsync>g__Awaited|25_0(ResourceInvoker invoker, Task " +
                "lastTask, State next, Scope scope, Object state, Boolean isCompleted)\"},\"formatters\":[]," +
                "\"contentTypes\":[],\"declaredType\":null,\"statusCode\":null}", InstallAppResponseDTO.class);
        log.info("结果为：{}",  installAppResponseDTO);
        if (installAppResponseDTO.getErrorCode() != null &&  installAppResponseDTO.getErrorCode()  == 0
                && StrUtil.isNotBlank(installAppResponseDTO.getAppId())) {
            log.info("组织{}通过三方应用，复制安装{}应用成功", "installAppResponseDTO.getProjectId()", "initRequestDTO.getId()");
        } else {
            log.error("组织{}通过三方应用，复制安装{}应用失败", "initRequestDTO.getProjectId()", "initRequestDTO.getId()");
        }
    }
}
