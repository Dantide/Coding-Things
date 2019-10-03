function [x,t] = one_cos(A, freq, phase, dur)
t = 0:1 / (20 * (freq / (2 * pi))):dur;
x = A * cos(freq * t + phase);
