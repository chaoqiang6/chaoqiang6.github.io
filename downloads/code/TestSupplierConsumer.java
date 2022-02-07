package lamdademo;

import lamdademo.vo.OperationVo;
import lamdademo.vo.PageVo;
import lombok.extern.slf4j.Slf4j;

import javax.management.ReflectionException;
import javax.naming.ReferralException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Slf4j(topic = "c.TestSupplierConsumer")
public class TestSupplierConsumer {

    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        PageVo pageVo = new PageVo();
        OperationVo operationVo = new OperationVo();
        byRefection(list, pageVo);
        byRefection(list, operationVo);
        pageVo.reset();
        operationVo.reset();
        list.add(new Object());
        byRefection(list, pageVo);
        byRefection(list, operationVo);


//        byTemplete(list,pageVo);
//        byTemplete(list,operationVo);
//        pageVo.reset();
//        operationVo.reset();
//        list.add(new Object());
//        byTemplete(list,pageVo);
//        byTemplete(list,operationVo);



    }

    private static void byTemplete(List<Object> list, PageVo pageVo) {
        setObjPassableRejectAble(list, () -> pageVo, vo -> vo.setPassable(true), vo -> vo.setRejectable(true));
        System.out.println(pageVo);
    }


    private static void byTemplete(List<Object> list, OperationVo operationVo) {
        setObjPassableRejectAble(list, () -> operationVo, vo -> vo.setPassable(true), vo -> vo.setRejectable(true));
        System.out.println(operationVo);
    }

    private static void byRefection(List<Object> list, PageVo pageVo){
        setObjPassableRejectAbleByRefection(list,pageVo);
        System.out.println(pageVo);
    }

    private static void byRefection(List<Object> list, OperationVo operationVo){
        setObjPassableRejectAbleByRefection(list,operationVo);
        System.out.println(operationVo);
    }

    /**
     * 使用反射方法修改传入的obj对象
     *
     * @param normalParams
     * @param obj          normalParams不为空时，设置obj可放行，否则，设置obj可批退
     */
    private static void setObjPassableRejectAbleByRefection(List<Object> normalParams, Object obj) {

        //一系列复杂的操作逻辑
        if (normalParams.isEmpty()) {
            Class<?> aClass = obj.getClass();
            try {
                Field rejectable = aClass.getDeclaredField("rejectable");
                rejectable.setAccessible(true);
                rejectable.set(obj, true);

            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
        } else {
            Class<?> aClass = obj.getClass();
            try {
                Field passable = aClass.getDeclaredField("passable");
                passable.setAccessible(true);
                passable.set(obj, true);

            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 使用模板方法模式搭配supplier,consumer实现方式
     *
     * @param normalParams
     * @param supplier       执行consumer操作的对象
     * @param passConsumer   执行放行操作
     * @param rejectConsumer 执行批退操作
     * @param <T>
     */
    private static <T> void setObjPassableRejectAble(List<Object> normalParams, Supplier<T> supplier, Consumer<T> passConsumer, Consumer<T> rejectConsumer) {
        T obj = supplier.get();
        if (normalParams.isEmpty()) {
            rejectConsumer.accept(obj);
        } else {
            passConsumer.accept(obj);
        }

    }


}
