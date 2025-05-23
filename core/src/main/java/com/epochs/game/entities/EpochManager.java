//Deprecated



//package com.epochs.game.entities;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class EpochManager {
//    private final List<Epoch> epochs;
//    private int currentEpochIndex;
//
//    public EpochManager() {
//        epochs = new ArrayList<>();
//        currentEpochIndex = 0;
//
//        // Definicje epok
//        epochs.add(new Epoch("Prehistoria", 6000, 1000));
//        epochs.add(new Epoch("Starożytność", 12000, 2000));
//        epochs.add(new Epoch("Średniowiecze", 24000, 4000));
//        epochs.add(new Epoch("Renesans", 48000, 8000));
//        epochs.add(new Epoch("Okres Prochu", 96000, 16000));
//        epochs.add(new Epoch("Wielkie Wojny", 192000, 32000));
//        epochs.add(new Epoch("Nowoczesność", 384000, 64000));
//        epochs.add(new Epoch("Przyszłość", Integer.MAX_VALUE, 128000));
//    }
//
//    public Epoch getCurrentEpoch() {
//        return epochs.get(currentEpochIndex);
//    }
//
//    public boolean canAdvanceEpoch(int currentExp) {
//        return currentEpochIndex < epochs.size() - 1 && currentExp >= epochs.get(currentEpochIndex).getExpCap();
//    }
//
//    public void advanceEpoch() {
//        if (currentEpochIndex < epochs.size() - 1) {
//            currentEpochIndex++;
//        } else {
//            throw new IllegalStateException("Gracz jest już w ostatniej epoce!");
//        }
//    }
//
//    public int getNextExpCap() {
//        if (currentEpochIndex < epochs.size() - 1) {
//            return epochs.get(currentEpochIndex + 1).getExpCap();
//        } else {
//            return Integer.MAX_VALUE;
//        }
//    }
//}
