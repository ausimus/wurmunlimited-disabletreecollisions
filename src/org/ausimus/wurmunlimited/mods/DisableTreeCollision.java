package org.ausimus.wurmunlimited.mods;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.bytecode.Descriptor;
import org.gotti.wurmunlimited.modloader.classhooks.HookManager;
import org.gotti.wurmunlimited.modloader.classhooks.InvocationHandlerFactory;
import org.gotti.wurmunlimited.modloader.interfaces.Initable;
import org.gotti.wurmunlimited.modloader.interfaces.WurmClientMod;
public class DisableTreeCollision implements WurmClientMod, Initable
{
    @Override
    public void init() {
        ClassPool classPool = HookManager.getInstance().getClassPool();
        String descriptor;
        descriptor = Descriptor.ofMethod(CtClass.booleanType, new CtClass[]
                {CtClass.byteType,CtClass.floatType});
        HookManager.getInstance().registerHook("com.wurmonline.client.renderer.cell.TreePosition","willCollide",descriptor,
                new InvocationHandlerFactory()
                {
                    @Override
                    public InvocationHandler createInvocationHandler()
                    {
                        return new InvocationHandler()
                        {
                            @Override
                            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
                            {
                                    return false;
                            }
                        };
                    }
                });
    }
}