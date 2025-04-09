package Packit.spring.domain.enums;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends StateMachineConfigurerAdapter<OrderState, OrderEvent> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<OrderState, OrderEvent> config) throws Exception {
        super.configure(config); // ✅ 반드시 호출
        config
                .withConfiguration()
                .autoStartup(true)
                .listener(new StateMachineListenerAdapter<>() {
                    @Override
                    public void stateChanged(State<OrderState, OrderEvent> from, State<OrderState, OrderEvent> to) {
                        System.out.println("✅ 상태 변경: " + (from != null ? from.getId() : "[NONE]") + " → " + to.getId());
                    }

                    @Override
                    public void stateMachineError(StateMachine<OrderState, OrderEvent> stateMachine, Exception exception) {
                        System.err.println("❌ 상태머신 오류 발생: " + exception.getMessage());
                    }
                });
    }

    @Override
    public void configure(StateMachineStateConfigurer<OrderState, OrderEvent> states) throws Exception {
        super.configure(states); // ✅ 반드시 호출
        states
                .withStates()
                .initial(OrderState.REQUESTED)
                .state(OrderState.PACKED)
                .state(OrderState.PICKUP_READY)
                .state(OrderState.PICKED_UP)
                .state(OrderState.CANCELLED);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderState, OrderEvent> transitions) throws Exception {
        super.configure(transitions); // ✅ 반드시 호출
        transitions
                .withExternal().source(OrderState.REQUESTED).target(OrderState.PACKED).event(OrderEvent.PACK)
                .and()
                .withExternal().source(OrderState.PACKED).target(OrderState.PICKUP_READY).event(OrderEvent.MAKE_READY)
                .and()
                .withExternal().source(OrderState.PICKUP_READY).target(OrderState.PICKED_UP).event(OrderEvent.PICKUP)
                .and()
                .withExternal().source(OrderState.REQUESTED).target(OrderState.CANCELLED).event(OrderEvent.CANCEL)
                .and()
                .withExternal().source(OrderState.PACKED).target(OrderState.CANCELLED).event(OrderEvent.CANCEL);
    }
}
