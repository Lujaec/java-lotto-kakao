package lotto.model.vo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
class PurchaseCountTest {

    @ParameterizedTest(name = "개수가 {0}일 때")
    @ValueSource(ints = {1, 2, 5, 10, 100, 1000})
    void 올바르게_구입_개수를_생성할_수_있다(final int count) {
        Assertions.assertDoesNotThrow(() -> new PurchaseCount(count));
    }

    @ParameterizedTest(name = "개수가 {0}일 때")
    @ValueSource(ints = {0, -1, -20, -100, -1000})
    void 구입_개수가_자연수가_아니면_예외_처리한다(final int count) {
        assertThatThrownBy(() -> new PurchaseCount(count))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("구입 개수는 1개 이상이어야 합니다.");
    }

    @ParameterizedTest(name = "총 구입 개수가 {0}개이고 수동 구매 개수가 {1}개일 때")
    @CsvSource(value = {"1:2", "2:4", "5:10", "10:15", "100:510", "1000:5010"}, delimiter = ':')
    void 수동_구매_개수가_구입_개수보다_많을_경우(final int purchaseCount, final int manualCount) {
        // given
        final PurchaseCount purchase = new PurchaseCount(purchaseCount);
        final ManualLottoCount manual = new ManualLottoCount(manualCount);

        // when & then
        assertThatThrownBy(() -> purchase.calculateAutoLottoCount(manual))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 구매 개수는 구입 개수보다 작거나 같아야 합니다.");
    }

    @ParameterizedTest(name = "총 구입 개수가 {0}개이고 수동 구매 개수가 {1}개일 때")
    @CsvSource(value = {"1:0:1", "2:1:1", "5:2:3", "10:5:5", "100:10:90", "1000:100:900"}, delimiter = ':')
    void 자동_구매_개수를_올바르게_계산한다(final int purchaseCount, final int manualCount, final int expected) {
        // given
        final PurchaseCount purchase = new PurchaseCount(purchaseCount);
        final ManualLottoCount manual = new ManualLottoCount(manualCount);

        // when
        final int autoCount = purchase.calculateAutoLottoCount(manual);

        // then
        assertThat(autoCount).isEqualTo(expected);
    }
}

