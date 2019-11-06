import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigInteger;

import static org.junit.Assert.assertTrue;

public class PerfectWorkerTest {

    @Test
    public void isPerfect() {

        Buffer bufferMock = Mockito.mock(Buffer.class);
        Barrier barrierMock = Mockito.mock(Barrier.class);

        PerfectWorker worker = new PerfectWorker(bufferMock, barrierMock);

        assertTrue(worker.isPerfect(new BigInteger("137438691328")));
    }
}
