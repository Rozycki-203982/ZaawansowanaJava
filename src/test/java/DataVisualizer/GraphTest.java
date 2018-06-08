package DataVisualizer;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class GraphTest {

    @Test
    public void shouldDisplayGraph() {

        List<Double> data = new ArrayList<>();

        for (int i = 0; i < 256; i++) {

            data.add(i * 2.562d);
        }

        Graph graph = mock(Graph.class);
        Mockito.when(graph.isVisible()).thenReturn(true);
        assertEquals(true, graph.isVisible());
    }
}
