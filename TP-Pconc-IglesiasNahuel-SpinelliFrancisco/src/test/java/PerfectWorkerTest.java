import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigInteger;

import static org.junit.Assert.assertTrue;

public class PerfectWorkerTest {

    @Test
    public void isPerfect() {

        Buffer bufferMock = Mockito.mock(Buffer.class);

        PerfectWorker worker = new PerfectWorker(bufferMock);

        assertTrue(worker.isPerfect(new BigInteger("137438691328")));
    }
}
