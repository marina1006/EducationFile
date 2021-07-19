import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTest extends TestCase {
    List<Station> routeWithConnection;
    List <Station> routeOneConnection;
    List <Station> routeTwoConnection;
    @Override
    protected void setUp() throws Exception {

        StationIndex stationIndex = new StationIndex();
        Line line2 = new Line (2, "Вторая");
        Line line3 = new Line(3, "Третья");
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);
        Station st1l3 = new Station("Приморскаая",line3);
        Station st2l3 = new Station("Василеостровская",line3);
        Station st3l3 = new Station("Гостиный двор",line3);

        line3.addStation(st1l3);
        line3.addStation(st2l3);
        line3.addStation(st3l3);
        Station st1l2 = new Station("Невский проспект",line2);
        Station st2l2 = new Station("Сенная площадь",line2);

        line3.addStation(st1l2);
        line3.addStation(st2l2);

        stationIndex.addStation(st1l3);
        stationIndex.addStation(st2l3);
        stationIndex.addStation(st3l3);
        stationIndex.addStation(st1l2);
        stationIndex.addStation(st2l2);

        List<Station> connection3to2 = new ArrayList<>();
        connection3to2.add(st2l3);
        connection3to2.add(st1l2);
        stationIndex.addConnection(connection3to2);
        RouteCalculator routeCalculator = new RouteCalculator(stationIndex);


    }
public void testCalculateDuration()
{
    double actual = RouteCalculator.calculateDuration(routeTwoConnection);
    double expected = 12.5;
    assertEquals(expected, actual);
}
    @Override
    protected void tearDown() throws Exception {

    }
}
