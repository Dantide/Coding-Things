function [X] = fourier_plot( samples, f0, K )
% samples = A row vector of samples of x(t) from the time interval [0,To],
% where To = 1/fo, with samples taken every Ts = To/N seconds, where N = 44,100.
k = -K:1:K;
T0 = length(samples) / 44100;
X = Fourier_coefs(T0,samples,K);

figure();
subplot(2, 1, 1);
stem(k, abs(X), "LineWidth", 2);
title("Magnitude Spectrum of x(t)", "FontSize", 16);
xlabel("k", "FontSize", 14);
ylabel("|a_k|", "FontSize", 14);
grid;

subplot(2, 1, 2);
stem(k, angle(X), "LineWidth", 2);
title("Phase Spectrum of x(t)", "FontSize", 16);
xlabel("k", "FontSize", 14);
ylabel("arg(a_k)", "FontSize", 14);
grid;
