package nextstep.subway.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

@Embeddable
public class Sections {

    @OneToMany(mappedBy = "line", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Section> sections;

    protected Sections() {
    }

    private Sections(List<Section> sections) {
        this.sections = new ArrayList<>(sections);
    }

    public static Sections from(List<Section> sections) {
        return new Sections(sections);
    }

    public void add(Section section) {
        sections.add(section);
    }

    public List<Station> getStations() {
        return sections.stream()
            .map(Section::getStations)
            .flatMap(Collection::stream)
            .distinct()
            .collect(Collectors.toList());
    }
}