package Packit.spring.domain.enums;

public enum OrderStatus {
    REQUESTED {
        @Override
        public String nextAction() {
            return "상품을 포장하세요.";
        }
    },
    PACKED {
        @Override
        public String nextAction() {
            return "픽업 준비를 하세요.";
        }
    },
    PICKUP_READY {
        @Override
        public String nextAction() {
            return "고객이 물건을 가져갈 수 있습니다.";
        }
    },
    PICKED_UP {
        @Override
        public String nextAction() {
            return "주문이 완료되었습니다.";
        }
    },
    CANCELLED {
        @Override
        public String nextAction() {
            return "주문이 취소되었습니다.";
        }
    };

    // 공통 추상 메서드 정의 (모든 상태가 이걸 오버라이드해야 함)
    public abstract String nextAction();
}
