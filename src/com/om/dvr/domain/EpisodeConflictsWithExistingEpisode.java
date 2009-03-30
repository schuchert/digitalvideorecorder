package com.om.dvr.domain;

public class EpisodeConflictsWithExistingEpisode extends RuntimeException {
    public final Episode episode;

    public EpisodeConflictsWithExistingEpisode(Episode episode) {
        super(String.format("Episode already exists: %s", episode));
        this.episode = episode;
        // TODO Auto-generated constructor stub
    }

    private static final long serialVersionUID = 1L;

}
